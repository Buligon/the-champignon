package model.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.connector.ConexaoJPQL;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.vo.Enderecos;
import model.vo.Fornecedores;

public class FornecedoresDAOImpl implements FornecedoresDAO {
    EntityManager manager;
    
    public FornecedoresDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Fornecedores fornecedor) {
        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Fornecedores fornecedor) {
        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idPessoa) {
        manager.getTransaction().begin();
        
        Fornecedores fornecedor = manager.find(Fornecedores.class, idPessoa);

        if (fornecedor != null) {
            fornecedor.setCancelado(1);
            manager.merge(fornecedor);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Fornecedores> listarTodos() {
        List<Fornecedores> fornecedores;
        
        try {
            TypedQuery<Fornecedores> query = manager.createQuery("SELECT f FROM Fornecedores f WHERE f.cancelado <> 1", Fornecedores.class);

            fornecedores = query.getResultList();
        } catch (NoResultException e) {
            fornecedores = new ArrayList<>(); 
        } catch (Exception e) {
            fornecedores = new ArrayList<>();
        }
        
        return fornecedores;
    }
    
    @Override
    public Field[] listarCampos() {

        Field[] campos = Fornecedores.class.getDeclaredFields();      
        return campos;
    }
    
    @Override
    public Fornecedores obterPorId(long idPessoa) {
        TypedQuery<Fornecedores> query = manager.createQuery(
            "SELECT e FROM Fornecedores e WHERE e.id = :idPessoa", Fornecedores.class
        );

        query.setParameter("idPessoa", idPessoa);

        return query.getSingleResult();
    }
    
    @Override
    public Fornecedores obterFornecedorPorNome(String nome) {
        try {
            String jpql = "SELECT f FROM Fornecedores f WHERE f.razaoSocial = :nome";
            TypedQuery<Fornecedores> query = manager.createQuery(jpql, Fornecedores.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public List<Fornecedores> filtrar(String campo, String filtro) {
        
        String stringQuery = "";
        List<Fornecedores> fornecedores = new ArrayList<>();
        Field[] camposEndereco = Enderecos.class.getDeclaredFields(); 
        List<String> camposEnd = new ArrayList<>();
        
        // Cria o array com os campos em endereco
        for(Field campoEnd : camposEndereco) {
            camposEnd.add(campoEnd.getName());
        }
       
        String stringCampos = String.join(" ", camposEnd);
        
        
        //verifica se algum campo de endereco esta sendo filtrado
        if(stringCampos.contains(campo) == false || campo == "id"){            
            stringQuery = "SELECT c FROM Fornecedores c WHERE c."+campo+" LIKE '"+filtro+"%'";
        }
        else if(stringCampos.contains(campo) == true && campo != "id"){             
            stringQuery = "SELECT c FROM Fornecedores c INNER JOIN c.endereco e WHERE e."+campo+" LIKE '"+filtro+"%'";
            
        }
                
        if(filtro.length() != 0){
            try {
                TypedQuery<Fornecedores> query = (TypedQuery<Fornecedores>) manager.createQuery(stringQuery);

                fornecedores = query.getResultList();
            } catch (NoResultException e) {
                fornecedores = new ArrayList<>(); 
            } catch (Exception e) {
                fornecedores = new ArrayList<>();
            }
            
        }
        else{
             fornecedores = listarTodos();}
        
        return fornecedores;
        
    }
}

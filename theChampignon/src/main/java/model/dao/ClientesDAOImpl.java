package model.dao;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Clientes;
import model.vo.Enderecos;
import model.vo.Pessoas;

public class ClientesDAOImpl implements ClientesDAO {
    EntityManager manager;
    
    public ClientesDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Clientes cliente) {
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Clientes cliente) {
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idPessoa) {
        manager.getTransaction().begin();
        
        Clientes cliente = manager.find(Clientes.class, idPessoa);

        if (cliente != null) {
            cliente.setCancelado(1);
            manager.merge(cliente);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Clientes> listarTodos() {
        List<Clientes> clientes;

        try {
            TypedQuery<Clientes> query = manager.createQuery("SELECT e FROM Clientes e WHERE e.cancelado <> 1", Clientes.class);

            clientes = query.getResultList();
        } catch (NoResultException e) {
            clientes = new ArrayList<>(); 
        } catch (Exception e) {
            clientes = new ArrayList<>();
        }

        return clientes;
    }
    
     public Field[] listarCampos() {

        Field[] campos = Pessoas.class.getDeclaredFields();      
        return campos;
    }
    
    @Override
    public Clientes obterPorId(long idPessoa) {
        TypedQuery<Clientes> query = manager.createQuery(
            "SELECT e FROM Clientes e WHERE e.id = :idPessoa", Clientes.class
        );

        query.setParameter("idPessoa", idPessoa);

        return query.getSingleResult();
    }
    
    @Override
    public List<Clientes> filtrar(String campo, String filtro) {
        
        String stringQuery = "";
        List<Clientes> clientes = new ArrayList<>();
        Field[] camposEndereco = Enderecos.class.getDeclaredFields(); 
        List<String> camposEnd = new ArrayList<>();
        
        // Cria o array com os campos em endereco
        for(Field campoEnd : camposEndereco) {
            camposEnd.add(campoEnd.getName());
        }
       
        String stringCampos = String.join(" ", camposEnd);
        
        
        //verifica se algum campo de endereco esta sendo filtrado
        if(stringCampos.contains(campo) == false || campo == "id"){            
            stringQuery = "SELECT c FROM Clientes c WHERE c."+campo+" LIKE '"+filtro+"%'";
        }
        else if(stringCampos.contains(campo) == true && campo != "id"){             
            stringQuery = "SELECT c FROM Clientes c INNER JOIN c.endereco e WHERE e."+campo+" LIKE '"+filtro+"%'";
            
        }
        
        System.out.println("\n log query -> "+stringQuery);
        
        if(filtro.length() != 0){
            try {
                TypedQuery<Clientes> query = (TypedQuery<Clientes>) manager.createQuery(stringQuery);

                clientes = query.getResultList();
            } catch (NoResultException e) {
                clientes = new ArrayList<>(); 
            } catch (Exception e) {
                clientes = new ArrayList<>();
            }
            
        }
        else{
             clientes = listarTodos();}
        
        return clientes;
        
    }
}


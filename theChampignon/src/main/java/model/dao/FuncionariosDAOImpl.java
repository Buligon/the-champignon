package model.dao;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.connector.ConexaoJPQL;
import model.vo.Funcionarios;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.vo.Enderecos;
import model.vo.Pessoas;

public class FuncionariosDAOImpl implements FuncionariosDAO {
    EntityManager manager;
    
    public FuncionariosDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Funcionarios funcionario) {
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Funcionarios funcionario) {
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idPessoa) {
        manager.getTransaction().begin();
        
        Funcionarios funcionario = manager.find(Funcionarios.class, idPessoa);

        if (funcionario != null) {
            funcionario.setCancelado(1);
            manager.merge(funcionario);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Funcionarios> listarTodos() {
        List<Funcionarios> funcionarios;
        
        try {
            
            TypedQuery<Funcionarios> query = manager.createQuery("SELECT f FROM Funcionarios f WHERE f.cancelado <> 1", Funcionarios.class);

            funcionarios = query.getResultList();
        } catch (NoResultException e) {
            funcionarios = new ArrayList<>(); 
        } catch (Exception e) {
            
            funcionarios = new ArrayList<>();
        }
        
        return funcionarios;
    }
    
    @Override
    public Field[] listarCampos() {
        
        Field[] camposFunc = Funcionarios.class.getDeclaredFields();    
        Field[] camposPessoas = Pessoas.class.getDeclaredFields();
                
        Field[] campos = (Field[]) Array.newInstance(camposFunc.getClass().getComponentType(), camposFunc.length + camposPessoas.length);;
        System.arraycopy(camposPessoas, 0, campos, 0, camposPessoas.length);
        System.arraycopy(camposFunc, 0, campos,  camposPessoas.length, camposFunc.length);
                
        return campos;
        
    }
    
    @Override
    public Funcionarios obterPorId(long idPessoa) {
        TypedQuery<Funcionarios> query = manager.createQuery(
            "SELECT f FROM Funcionarios f WHERE f.id = :idPessoa", Funcionarios.class
        );

        query.setParameter("idPessoa", idPessoa);

        return query.getSingleResult();
    }
    
    @Override
    public List<Funcionarios> filtrar(String campo, String filtro) {
        
        String stringQuery = "";
        List<Funcionarios> clientes = new ArrayList<>();
        Field[] camposEndereco = Enderecos.class.getDeclaredFields(); 
        List<String> camposEnd = new ArrayList<>();
        
        // Cria o array com os campos em endereco
        for(Field campoEnd : camposEndereco) {
            camposEnd.add(campoEnd.getName());
        }
       
        String stringCamposEnd = String.join(" ", camposEnd);
        String stringCamposDate = "admissao demissao";
        
        //verifica se algum campo de endereco esta sendo filtrado
        if(stringCamposEnd.contains(campo) == false || campo == "id" ){            
            stringQuery = "SELECT c FROM Funcionarios c WHERE c."+campo+" LIKE '"+filtro+"%'";
        }
        if(stringCamposEnd.contains(campo) == true && campo != "id"){             
            stringQuery = "SELECT c FROM Funcionarios c INNER JOIN c.endereco e WHERE e."+campo+" LIKE '"+filtro+"%'";
        }       
        if(stringCamposDate.contains(campo) == true){
            stringQuery = "SELECT f FROM Funcionarios f WHERE function('date_format' ,f."+campo+", '%d/%m/%Y') LIKE '"+filtro+"%'";
        }
                
        if(filtro.length() != 0){
            try {
                TypedQuery<Funcionarios> query = (TypedQuery<Funcionarios>) manager.createQuery(stringQuery);

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

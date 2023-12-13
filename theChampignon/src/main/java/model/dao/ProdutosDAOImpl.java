package model.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.connector.ConexaoJPQL;
import model.vo.Produtos;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ProdutosDAOImpl implements ProdutosDAO {
    EntityManager manager;
    
    public ProdutosDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Produtos produto) {
        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Produtos produto) {
        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idProduto) {
        manager.getTransaction().begin();
        
        Produtos produto = manager.find(Produtos.class, idProduto);

        if (produto != null) {
            produto.setCancelado(1);
            manager.merge(produto);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Produtos> listarTodos() {
        List<Produtos> produtos;

        try {
            // Create a JPQL query to select records from the "Produtos" entity where cancelado is not equal to 1
            TypedQuery<Produtos> query = manager.createQuery("SELECT e FROM Produtos e WHERE e.cancelado <> 1", Produtos.class);

            // Execute the query and get the results
            produtos = query.getResultList();
        } catch (NoResultException e) {
            produtos = new ArrayList<>(); // Handle no results, if needed
        } catch (Exception e) {
            // Handle other exceptions as needed
            produtos = new ArrayList<>();
        }

        return produtos;
    }
    
    public Field[] listarCampos() {
        Field[] campos = Produtos.class.getDeclaredFields();      
        return campos;
    }
    
    @Override
    public Produtos obterPorId(long idProduto) {
        TypedQuery<Produtos> query = manager.createQuery(
            "SELECT p FROM Produtos p WHERE p.id = :idProduto", Produtos.class
        );

        query.setParameter("idProduto", idProduto);

        return query.getSingleResult();
    }
    
    @Override
    public List<Produtos> filtrar(String campo, String filtro) {
        
        String stringQuery = "";
        List<Produtos> produtos = new ArrayList<>();
               
        if(campo == "especie"){             
            stringQuery = "SELECT p FROM Produtos p INNER JOIN p.especie e WHERE e.descricao LIKE '"+filtro+"%'";    
        }
        else if(campo == "unidade"){             
            stringQuery = "SELECT p FROM Produtos p INNER JOIN p.unidade u WHERE u.descricao LIKE '"+filtro+"%'";
        }
        else{            
            stringQuery = "SELECT p FROM Produtos p WHERE p."+campo+" LIKE '"+filtro+"%'";
        }
                
        if(filtro.length() != 0){
            try {
                TypedQuery<Produtos> query = (TypedQuery<Produtos>) manager.createQuery(stringQuery);

                produtos = query.getResultList();
            } catch (NoResultException e) {
                produtos = new ArrayList<>(); 
            } catch (Exception e) {
                produtos = new ArrayList<>();
            }
            
        }
        else{
             produtos = listarTodos();}
        
        return produtos;
        
    }
}

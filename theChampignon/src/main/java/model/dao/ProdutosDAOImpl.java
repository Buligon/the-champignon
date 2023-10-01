package model.dao;

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
    public void excluir(Produtos produto) {
        produto.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();
    }

    @Override
    public List<Produtos> listarTodos() {
        List<Produtos> produtos;
        
        try {
            // Create a JPQL query to select all records from the "Produtos" entity
            TypedQuery<Produtos> query = manager.createQuery("SELECT e FROM Produtos e", Produtos.class);

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
    
    
    @Override
    public List<Produtos> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

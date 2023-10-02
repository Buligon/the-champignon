package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.connector.ConexaoJPQL;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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
    public void excluir(Fornecedores fornecedor) {
        fornecedor.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();
    }

    @Override
    public List<Fornecedores> listarTodos() {
        List<Fornecedores> produtos;
        
        try {
            // Create a JPQL query to select all records from the "Produtos" entity
            TypedQuery<Fornecedores> query = manager.createQuery("SELECT f FROM Fornecedores f", Fornecedores.class);

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
    public List<Fornecedores> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

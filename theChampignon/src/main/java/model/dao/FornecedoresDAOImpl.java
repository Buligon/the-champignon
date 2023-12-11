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
    public Fornecedores obterPorId(long idPessoa) {
        TypedQuery<Fornecedores> query = manager.createQuery(
            "SELECT e FROM Fornecedores e WHERE e.id = :idPessoa", Fornecedores.class
        );

        query.setParameter("idPessoa", idPessoa);

        return query.getSingleResult();
    }
    
    @Override
    public List<Fornecedores> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Clientes;

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
    
    @Override
    public Clientes obterPorId(long idPessoa) {
        TypedQuery<Clientes> query = manager.createQuery(
            "SELECT e FROM Clientes e WHERE e.id = :idPessoa", Clientes.class
        );

        query.setParameter("idPessoa", idPessoa);

        return query.getSingleResult();
    }
    
    @Override
    public List<Clientes> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

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
    public void excluir(Clientes cliente) {
        cliente.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
    }

    @Override
    public List<Clientes> listarTodos() {
        List<Clientes> pessoa;
        
        try {
            TypedQuery<Clientes> query = manager.createQuery("SELECT c FROM Clientes c", Clientes.class);
            pessoa = query.getResultList();
        } catch (NoResultException e) {
            pessoa = new ArrayList<>(); // Handle no results
        } catch (Exception e) {
            pessoa = new ArrayList<>();
        }
        
        return pessoa;
    }
    
    @Override
    public List<Clientes> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

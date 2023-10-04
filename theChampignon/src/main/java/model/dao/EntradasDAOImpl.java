package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Entradas;

public class EntradasDAOImpl implements EntradasDAO {
    EntityManager manager;
    
    public EntradasDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Entradas entrada) {
        manager.getTransaction().begin();
        manager.persist(entrada);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Entradas entrada) {
        manager.getTransaction().begin();
        manager.persist(entrada);
        manager.getTransaction().commit();
    }

    /*@Override
    public void excluir(Entradas entrada) {
        venda.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(venda);
        manager.getTransaction().commit();
    }*/

    @Override
    public List<Entradas> listarTodos() {
        List<Entradas> entrada;
        
        try {
            TypedQuery<Entradas> query = manager.createQuery("SELECT e FROM Entradas e", Entradas.class);

            // Execute the query and get the results
            entrada = query.getResultList();
        } catch (NoResultException e) {
            entrada = new ArrayList<>(); // Handle no results, if needed
        } catch (Exception e) {
            // Handle other exceptions as needed
            entrada = new ArrayList<>();
        }
        
        return entrada;
    }
    
    
    @Override
    public List<Entradas> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.connector.ConexaoJPQL;
import model.vo.Unidades;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UnidadesDAOImpl implements UnidadesDAO {
    EntityManager manager;
    
    public UnidadesDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Unidades unidade) {
        manager.getTransaction().begin();
        manager.persist(unidade);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Unidades unidade) {
        manager.getTransaction().begin();
        manager.persist(unidade);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(Unidades unidade) {
        unidade.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(unidade);
        manager.getTransaction().commit();
    }

    @Override
    public List<Unidades> listarTodos() {
        List<Unidades> unidades;
        
        try {
            // Create a JPQL query to select all records from the "Especies" entity
            TypedQuery<Unidades> query = manager.createQuery("SELECT e FROM Unidades e", Unidades.class);

            // Execute the query and get the results
            unidades = query.getResultList();
        } catch (NoResultException e) {
            unidades = new ArrayList<>(); // Handle no results, if needed
        } catch (Exception e) {
            // Handle other exceptions as needed
            unidades = new ArrayList<>();
        }
        
        return unidades;
    }
    
    
    @Override
    public List<Unidades> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

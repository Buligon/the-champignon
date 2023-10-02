package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.connector.ConexaoJPQL;
import model.vo.Especies;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class EspeciesDAOImpl implements EspeciesDAO {
    EntityManager manager;
    
    public EspeciesDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Especies especie) {
        manager.getTransaction().begin();
        manager.persist(especie);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Especies especie) {
        manager.getTransaction().begin();
        manager.persist(especie);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(Especies especie) {
        especie.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(especie);
        manager.getTransaction().commit();
    }

    @Override
    public List<Especies> listarTodos() {
        List<Especies> especies;
        
        try {
            TypedQuery<Especies> query = manager.createQuery("SELECT e FROM Especies e", Especies.class);
            especies = query.getResultList();
        } catch (NoResultException e) {
            especies = new ArrayList<>(); // Handle no results
        } catch (Exception e) {
            especies = new ArrayList<>();
        }
        
        return especies;
    }
    
    @Override
    public List<Especies> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
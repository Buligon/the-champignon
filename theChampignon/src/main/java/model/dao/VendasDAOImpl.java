package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Vendas;

public class VendasDAOImpl implements VendasDAO {
    EntityManager manager;
    
    public VendasDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Vendas venda) {
        manager.getTransaction().begin();
        manager.persist(venda);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Vendas venda) {
        manager.getTransaction().begin();
        manager.persist(venda);
        manager.getTransaction().commit();
    }

    /*@Override
    public void excluir(Vendas venda) {
        venda.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(venda);
        manager.getTransaction().commit();
    }*/

    @Override
    public List<Vendas> listarTodos() {
        List<Vendas> venda;
        
        try {
            TypedQuery<Vendas> query = manager.createQuery("SELECT v FROM Vendas v", Vendas.class);

            // Execute the query and get the results
            venda = query.getResultList();
        } catch (NoResultException e) {
            venda = new ArrayList<>(); // Handle no results, if needed
        } catch (Exception e) {
            // Handle other exceptions as needed
            venda = new ArrayList<>();
        }
        
        return venda;
    }
    
    
    @Override
    public List<Vendas> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Pessoas;

public class PessoasDAOImpl implements PessoasDAO {
    EntityManager manager;
    
    public PessoasDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Pessoas pessoa) {
        manager.getTransaction().begin();
        manager.persist(pessoa);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Pessoas pessoa) {
        manager.getTransaction().begin();
        manager.persist(pessoa);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(Pessoas pessoa) {
        pessoa.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(pessoa);
        manager.getTransaction().commit();
    }

    @Override
    public List<Pessoas> listarTodos() {
        List<Pessoas> pessoa;
        
        try {
            TypedQuery<Pessoas> query = manager.createQuery("SELECT p FROM Pessoas p", Pessoas.class);
            pessoa = query.getResultList();
        } catch (NoResultException e) {
            pessoa = new ArrayList<>(); // Handle no results
        } catch (Exception e) {
            pessoa = new ArrayList<>();
        }
        
        return pessoa;
    }
    
    @Override
    public List<Pessoas> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

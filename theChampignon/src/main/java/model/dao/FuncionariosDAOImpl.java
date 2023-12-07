package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.connector.ConexaoJPQL;
import model.vo.Funcionarios;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class FuncionariosDAOImpl implements FuncionariosDAO {
    EntityManager manager;
    
    public FuncionariosDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Funcionarios funcionario) {
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Funcionarios funcionario) {
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idPessoa) {
        manager.getTransaction().begin();
        
        Funcionarios funcionario = manager.find(Funcionarios.class, idPessoa);

        if (funcionario != null) {
            funcionario.setCancelado(1);
            manager.merge(funcionario);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Funcionarios> listarTodos() {
        List<Funcionarios> funcionarios;
        
        try {
            
            TypedQuery<Funcionarios> query = manager.createQuery("SELECT f FROM Funcionarios f WHERE f.cancelado <> 1", Funcionarios.class);

            funcionarios = query.getResultList();
        } catch (NoResultException e) {
            funcionarios = new ArrayList<>(); 
        } catch (Exception e) {
            
            funcionarios = new ArrayList<>();
        }
        
        return funcionarios;
    }
    
    @Override
    public Funcionarios obterPorId(long idPessoa) {
        TypedQuery<Funcionarios> query = manager.createQuery(
            "SELECT f FROM Funcionarios f WHERE f.id = :idPessoa", Funcionarios.class
        );

        query.setParameter("idPessoa", idPessoa);

        return query.getSingleResult();
    }
    
    @Override
    public List<Funcionarios> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}

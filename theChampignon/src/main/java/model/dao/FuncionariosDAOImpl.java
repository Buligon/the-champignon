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
    public void excluir(Funcionarios funcionario) {
        funcionario.setCancelado(1);
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
    }

    @Override
    public List<Funcionarios> listarTodos() {
        List<Funcionarios> funcionarios;
        
        try {
            // Create a JPQL query to select all records from the "Funcionario" entity
            TypedQuery<Funcionarios> query = manager.createQuery("SELECT e FROM Funcionario e", Funcionarios.class);

            // Execute the query and get the results
            funcionarios = query.getResultList();
        } catch (NoResultException e) {
            funcionarios = new ArrayList<>(); // Handle no results, if needed
        } catch (Exception e) {
            // Handle other exceptions as needed
            funcionarios = new ArrayList<>();
        }
        
        return funcionarios;
    }
    
    
    @Override
    public List<Funcionarios> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

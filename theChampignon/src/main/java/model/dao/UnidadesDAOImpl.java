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
    public void atualizar(long idUnidade, String descricaoNova) {
         manager.getTransaction().begin();
         
        Unidades unidade = manager.find(Unidades.class, idUnidade);

        if (unidade != null) {
            unidade.setDescricao(descricaoNova);
            manager.merge(unidade);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idUnidade) {
        manager.getTransaction().begin();
        
        Unidades unidade = manager.find(Unidades.class, idUnidade);

        if (unidade != null) {
            unidade.setCancelado(1);
            manager.merge(unidade);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Unidades> listarTodos() {
        // Não retorna os registros onde cancelado == 1
        TypedQuery<Unidades> query = manager.createQuery("SELECT u FROM Unidades u WHERE u.cancelado <> 1", Unidades.class);
        return query.getResultList();
    }
    
    
    @Override
    public List<Unidades> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

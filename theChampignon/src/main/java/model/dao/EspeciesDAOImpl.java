package model.dao;

import java.util.List;
import model.connector.ConexaoJPQL;
import model.vo.Especies;
import javax.persistence.EntityManager;
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
    public void atualizar(long idEspecie, String descricaoNova) {
         manager.getTransaction().begin();
         
        Especies especie = manager.find(Especies.class, idEspecie);

        if (especie != null) {
            especie.setDescricao(descricaoNova);
            manager.merge(especie);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idEspecie) {
        manager.getTransaction().begin();
        
        Especies especie = manager.find(Especies.class, idEspecie);

        if (especie != null) {
            especie.setCancelado(1);
            manager.merge(especie);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public List<Especies> listarTodos() {
        // Não retorna os registros onde cancelado == 1
        TypedQuery<Especies> query = manager.createQuery("SELECT e FROM Especies e WHERE e.cancelado <> 1", Especies.class);
        return query.getResultList();
    }
    
    @Override
    public List<Especies> filtrar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

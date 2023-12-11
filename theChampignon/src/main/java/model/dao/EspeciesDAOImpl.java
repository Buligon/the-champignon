package model.dao;

import java.lang.reflect.Field;
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
    public Field[] listarCampos() {

        Field[] campos = Especies.class.getDeclaredFields();      
        return campos;
    }
    
    @Override
    public List<Especies> filtrar(String campo, String filtro) {
        
        String stringQuery = "";
        List<Especies> especies = new ArrayList<>();
              
        stringQuery = "SELECT e FROM Especies e WHERE e."+campo+" LIKE '"+filtro+"%'";
                
        if(filtro.length() != 0){
            try {
                TypedQuery<Especies> query = (TypedQuery<Especies>) manager.createQuery(stringQuery);

                especies = query.getResultList();
            } catch (NoResultException e) {
                especies = new ArrayList<>(); 
            } catch (Exception e) {
                especies = new ArrayList<>();
            }
            
        }
        else{
             especies = listarTodos();}
        
        return especies;
        
    }
}

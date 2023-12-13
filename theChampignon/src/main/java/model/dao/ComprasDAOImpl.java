package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Compras;

public class ComprasDAOImpl implements ComprasDAO {

    EntityManager manager;

    public ComprasDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }

    @Override
    public void salvar(Compras compra) {
        manager.getTransaction().begin();
        manager.persist(compra);
        manager.getTransaction().commit();
    }
    
    @Override
    public void atualizar(Compras compra) {
        manager.getTransaction().begin();
        manager.persist(compra);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idVenda) {
        manager.getTransaction().begin();

        Compras compra = manager.find(Compras.class, idVenda);

        if (compra != null) {
            compra.setCancelado(1);
            manager.merge(compra);
        }

        manager.getTransaction().commit();
    }

    @Override
    public List<Compras> listarTodos() {
        TypedQuery<Compras> query = manager.createQuery("SELECT c FROM Compras c WHERE c.cancelado = 0", Compras.class);
        return query.getResultList();
    }

    @Override
    public List<Compras> filtrar(String campo, String filtro) {
        String jpql = "SELECT c FROM Compras c WHERE LOWER(c." + campo + ") LIKE LOWER(:filtro)";
        TypedQuery<Compras> query = manager.createQuery(jpql, Compras.class);
        query.setParameter("filtro", "%" + filtro + "%");

        return query.getResultList();
    }

    @Override
    public Field[] listarCampos() {
        return Compras.class.getDeclaredFields();
    }
}

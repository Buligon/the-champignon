package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
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

    @Override
    public void excluir(long idVenda) {
        manager.getTransaction().begin();

        Vendas venda = manager.find(Vendas.class, idVenda);

        if (venda != null) {
            venda.setCancelado(1);
            manager.merge(venda);
        }

        manager.getTransaction().commit();
    }

    @Override
    public List<Vendas> listarTodos() {
        TypedQuery<Vendas> query = manager.createQuery("SELECT v FROM Vendas v WHERE v.cancelado = 0", Vendas.class);
        return query.getResultList();
    }

    @Override
    public List<Vendas> filtrar(String campo, String filtro) {
        String jpql = "SELECT v FROM Vendas v WHERE LOWER(v." + campo + ") LIKE LOWER(:filtro)";
        TypedQuery<Vendas> query = manager.createQuery(jpql, Vendas.class);
        query.setParameter("filtro", "%" + filtro + "%");

        return query.getResultList();
    }

    @Override
    public Field[] listarCampos() {
        return Vendas.class.getDeclaredFields();
    }
}

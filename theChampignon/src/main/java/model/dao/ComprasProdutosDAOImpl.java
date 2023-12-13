package model.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import model.connector.ConexaoJPQL;
import model.vo.ComprasProdutos;

public class ComprasProdutosDAOImpl implements ComprasProdutosDAO {

    EntityManager manager;

    public ComprasProdutosDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }

    @Override
    public void salvar(ComprasProdutos vendaProduto) {
        manager.getTransaction().begin();
        manager.persist(vendaProduto);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(ComprasProdutos vendaProduto) {
        manager.getTransaction().begin();
        manager.merge(vendaProduto);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idCompraProduto) {
        manager.getTransaction().begin();
        ComprasProdutos vendaProduto = manager.find(ComprasProdutos.class, idCompraProduto);
        vendaProduto.setCancelado(1);
        manager.getTransaction().commit();
    }

    @Override
    public List<ComprasProdutos> listarTodos() {
        return manager.createQuery("SELECT ct FROM ComprasProdutos ct WHERE ct.cancelado <> 1", ComprasProdutos.class).getResultList();
    }

    @Override
    public List<ComprasProdutos> retornarProdutos(long idCompra) {
        try {
            String jpql = "SELECT ct FROM ComprasProdutos ct WHERE ct.cancelado <> 1 AND ct.compra.id = :idCompra";
            return manager.createQuery(jpql, ComprasProdutos.class)
                    .setParameter("idCompra", idCompra)
                    .getResultList();
        } catch (IllegalArgumentException e) {
            // Handle the exception or log it
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}

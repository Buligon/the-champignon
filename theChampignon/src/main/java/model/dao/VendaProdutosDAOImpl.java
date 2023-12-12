package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import model.connector.ConexaoJPQL;
import model.vo.VendaProdutos;

public class VendaProdutosDAOImpl implements VendaProdutosDAO {

    EntityManager manager;

    public VendaProdutosDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }

    @Override
    public void salvar(VendaProdutos vendaProduto) {
        manager.getTransaction().begin();
        manager.persist(vendaProduto);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(VendaProdutos vendaProduto) {
        manager.getTransaction().begin();
        manager.merge(vendaProduto);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idVendaProduto) {
        manager.getTransaction().begin();
        VendaProdutos vendaProduto = manager.find(VendaProdutos.class, idVendaProduto);
        manager.remove(vendaProduto);
        manager.getTransaction().commit();
    }

    @Override
    public List<VendaProdutos> listarTodos() {
        return manager.createQuery("SELECT vp FROM VendaProdutos vp WHERE vp.cancelado <> 1", VendaProdutos.class).getResultList();
    }
}

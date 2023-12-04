package model.dao;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.connector.ConexaoJPQL;
import model.vo.Enderecos;

public class EnderecosDAOImpl implements EnderecoDAO {
    EntityManager manager;
    
    public EnderecosDAOImpl() {
        manager = ConexaoJPQL.getInstance();
    }
    
    @Override
    public void salvar(Enderecos endereco) {
        manager.getTransaction().begin();
        manager.persist(endereco);
        manager.getTransaction().commit();
    }

    @Override
    public void atualizar(Enderecos endereco) {
        manager.getTransaction().begin();
        manager.persist(endereco);
        manager.getTransaction().commit();
    }

    @Override
    public void excluir(long idEndereco) {
        manager.getTransaction().begin();
        
        Enderecos endereco = manager.find(Enderecos.class, idEndereco);

        if (endereco != null) {
            manager.merge(endereco);
        }
        
        manager.getTransaction().commit();
    }

    @Override
    public Enderecos obterPorId(long idEndereco) {
        TypedQuery<Enderecos> query = manager.createQuery(
            "SELECT e FROM Enderecos e WHERE e.id = :idEndereco", Enderecos.class
        );

        query.setParameter("idEndereco", idEndereco);

        return query.getSingleResult();    }
}

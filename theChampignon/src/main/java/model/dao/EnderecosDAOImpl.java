package model.dao;


import java.lang.reflect.Field;
import java.util.List;
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
    
    public Field[] listarCampos() {
  
        Field[] campos = Enderecos.class.getDeclaredFields();
        return campos;
    }

    @Override
    public Enderecos obterPorId(long idEndereco) {
        System.out.println("\n\n\n" + idEndereco);
        TypedQuery<Enderecos> query = manager.createQuery(
            "SELECT e FROM Enderecos e WHERE e.idEndereco = :idEndereco", Enderecos.class
        );

        query.setParameter("idEndereco", idEndereco);

        return query.getSingleResult();    }
}

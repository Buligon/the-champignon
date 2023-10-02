package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.dao.ClientesDAOImpl;
import model.dao.ClientesDAO;
import model.vo.Clientes;

public class GerarBanco {
    public static void main(String[] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("thechampignon");
        
        EntityManager manager = factory.createEntityManager();
        
        Clientes esp1 = new Clientes();
        esp1.setNome("teste");
        
        manager.getTransaction().begin();
        manager.persist(esp1);
        manager.getTransaction().commit();
        
        ClientesDAOImpl ClientesDAO = new ClientesDAOImpl();
        ClientesDAO.salvar(esp1);
        System.out.println(ClientesDAO.listarTodos());
    }
}

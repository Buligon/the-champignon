package utils;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.vo.ComprasProdutos;
import model.vo.Fornecedores;
import model.vo.Funcionarios;
import model.vo.Compras;
import model.vo.Produtos;

public class GerarBanco {
    public static void main(String[] args) {
        // Create an EntityManagerFactory and an EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("thechampignon");
        EntityManager em = emf.createEntityManager();


        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}

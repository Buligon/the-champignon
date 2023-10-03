package util;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.vo.EntradaProdutos;
import model.vo.Fornecedores;
import model.vo.Funcionarios;
import model.vo.Entradas;
import model.vo.Produtos;

public class GerarBanco {
    public static void main(String[] args) {
        // Create an EntityManagerFactory and an EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("thechampignon");
        EntityManager em = emf.createEntityManager();

        // Create sample data
        Date entryDate = new Date(); // Replace with an actual date
        Funcionarios funcionario = new Funcionarios(2500.0f, new Date(), null, null, "teste", "a@a.com", null, null); // Replace with actual parameters
        Fornecedores fornecedor = new Fornecedores("12345678901", "Fornecedor XYZ", "123-456-7890", null); // Replace with actual parameters

        // Create an Entradas instance
        Entradas entrada = new Entradas(entryDate, funcionario, fornecedor);

        // Create sample products
        Produtos produto1 = new Produtos("Product 1", 10, null, null, 10.0f, 20.0f);
        Produtos produto2 = new Produtos("Product 2", 5, null, null, 15.0f, 30.0f);

        // Create EntradaProdutos for each product
        EntradaProdutos entradaProduto1 = new EntradaProdutos(entrada, produto1, 10.0, 5);
        EntradaProdutos entradaProduto2 = new EntradaProdutos(entrada, produto2, 15.0, 3);

        // Add products to the entry
        entrada.getProdutos().add(entradaProduto1);
        entrada.getProdutos().add(entradaProduto2);

        // Start a transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // Persist the entry and associated products
        em.persist(entrada);

        // Commit the transaction
        transaction.commit();

        // Retrieve the entry by its ID
        Long entradaId = entrada.getId();
        Entradas retrievedEntrada = em.find(Entradas.class, entradaId);
        System.out.println("Retrieved Entrada: " + retrievedEntrada);

        // Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}

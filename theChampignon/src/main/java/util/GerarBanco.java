package util;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.vo.Produtos;
import model.vo.VendaProdutos;
import model.vo.Vendas;

public class GerarBanco {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("thechampignon");
        EntityManager em = emf.createEntityManager();

     
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            // Create a Venda
            Vendas venda = new Vendas();
            venda.setDataVenda(new Date());

            // Create some Produto
            Produtos produto1 = new Produtos();
            produto1.setDescricao("Product A");
            produto1.setValor(10.0f);

            Produtos produto2 = new Produtos();
            produto2.setDescricao("Product B");
            produto2.setValor(15.0f);
            
            em.persist(produto1);
            em.persist(produto2);

            // Create VendaProduto entries
            VendaProdutos vendaProduto1 = new VendaProdutos(venda, produto1, produto1.getValor(), 2);

            VendaProdutos vendaProduto2 = new VendaProdutos(venda, produto2, produto2.getValor(), 3);

            // Add VendaProduto entries to the Venda
            venda.getProdutos().add(vendaProduto1);
            venda.getProdutos().add(vendaProduto2);

            // Persist the Venda
            em.persist(venda);

            transaction.commit();
        
    }
}

package model.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EntradaProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Entradas entrada;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Produtos produto;

    private double preco;
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entradas getEntrada() {
        return entrada;
    }

    public void setEntrada(Entradas entrada) {
        this.entrada = entrada;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public EntradaProdutos(Entradas entrada, Produtos produto, double preco, int quantidade) {
        this.entrada = entrada;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public EntradaProdutos() {
    }
    
}

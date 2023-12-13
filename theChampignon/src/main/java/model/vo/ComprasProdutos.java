package model.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ComprasProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Compras compra;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Produtos produto;

    private float preco;
    private int quantidade;
    private int cancelado;

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ComprasProdutos(Compras entrada, Produtos produto, float preco, int quantidade) {
        this.compra = entrada;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public ComprasProdutos() {
    }
    
}

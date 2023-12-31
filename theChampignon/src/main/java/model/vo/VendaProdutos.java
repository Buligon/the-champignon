package model.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VendaProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendas venda;

    @ManyToOne
    private Produtos produto;

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }

    private float preco;
    private int quantidade;
    private int cancelado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
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

    public VendaProdutos(Vendas venda, Produtos produto, float preco, int quantidade) {
        this.venda = venda;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public VendaProdutos() {
    }
}

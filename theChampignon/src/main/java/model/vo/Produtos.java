package model.vo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private Long id;
    
    private String descricao;
    private int quantidade;
    @Embedded
    private Especies especie;
    @Embedded
    private Unidades unidade;
    private float custo;

    public Produtos(Long id, String descricao, int quantidade, Especies especie, Unidades unidade, float custo, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.especie = especie;
        this.unidade = unidade;
        this.custo = custo;
        this.valor = valor;
    }
    private float valor;
    private int cancelado;
    
    @ManyToMany
    @JoinTable(
        name = "fornecedores_produtos",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private List<Fornecedores> fornecedores = new ArrayList<>();
    
    public String getDescricao() {
        return descricao;
    }

    public List<Fornecedores> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedores> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Especies getEspecie() {
        return especie;
    }

    public void setEspecie(Especies especie) {
        this.especie = especie;
    }

    public Unidades getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidades unidade) {
        this.unidade = unidade;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }
    
    
}

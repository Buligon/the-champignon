package model.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.vo.Especies;

@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    
    private Long id;
    private String descricao;
    private int quantidade;
    private Especies especie;
    private Unidades unidade;
    private float custo;
    private float valor;
    private int cancelado;

    public String getDescricao() {
        return descricao;
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

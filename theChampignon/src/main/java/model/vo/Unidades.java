package model.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Unidades {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String descricao;

    public Unidades(String descricao) {
        this.descricao = descricao;
    }
    
    private int cancelado;
    
    public Unidades() {
    }
    
    public long getId() {
        return id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }
}

package model.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Especies {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String descricao;

    public Especies(String descricao) {
        this.descricao = descricao;
    }
    
    private int cancelado;

    public Especies() {
        
    }
        
    public Long getId() {
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

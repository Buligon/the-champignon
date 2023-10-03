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
    
    public Especies(String descricao) {
        this.descricao = descricao;
    }
    
    public Especies() {
        
    }
    
    private String descricao;
    private int cancelado;

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

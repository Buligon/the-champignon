package model.vo;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class Unidades {
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

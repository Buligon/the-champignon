package model.vo;

import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Giuvane Conti
 */
@Entity
public class Cliente extends Pessoas {
    
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long codCliente;
    
    //private String nome;
    
    @OneToMany(mappedBy = "cliente")
    List<Venda> vendas;
    
    @Embedded
    private Enderecos endereco;

//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
    
}

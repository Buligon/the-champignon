package model.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Clientes extends Pessoas {

    public Clientes(Long id, String cpf, String nome, String email, String telefone, Enderecos endereco) {
        super(id, cpf, nome, email, telefone, endereco);
    }
    /*@OneToMany(mappedBy = "cliente")
    List<Venda> vendas;*/
}

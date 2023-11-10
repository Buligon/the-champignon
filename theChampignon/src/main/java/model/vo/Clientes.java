package model.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Clientes extends PessoaFisica {

    public Clientes( String cpf, String nome, String email, String telefone, Enderecos endereco) {
        super(cpf, nome, email, telefone, endereco);
    }
    
    public Clientes() {

    }
    /*@OneToMany(mappedBy = "cliente")
    List<Venda> vendas;*/
}

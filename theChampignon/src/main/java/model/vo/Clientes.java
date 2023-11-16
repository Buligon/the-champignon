package model.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Clientes extends PessoaFisica {
    
    public Clientes(String nome, String email, String telefone, Enderecos endereco, String cpf) {
        super(nome, email, telefone, endereco, cpf);
    }
    
    public Clientes() {

    }
}

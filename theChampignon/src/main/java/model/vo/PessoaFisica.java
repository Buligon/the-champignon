package model.vo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PessoaFisica extends Pessoas{

    private String cpf;

    public PessoaFisica(String nome, String email, String telefone, Enderecos endereco, String cpf) {
        super(nome, email, telefone, endereco);
        this.cpf = cpf;
    }
    
    public PessoaFisica() {
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

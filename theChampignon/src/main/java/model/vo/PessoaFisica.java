package model.vo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PessoaFisica extends Pessoas{

    public PessoaFisica(String cpf, String nome, String email, String telefone, Enderecos endereco) {
        super(nome, email, telefone, endereco);
        this.cpf = cpf;
    }
    
     public PessoaFisica() {
     }
    
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

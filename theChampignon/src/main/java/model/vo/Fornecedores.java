package model.vo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedores extends Pessoas{

    private String cnpj;

    public Fornecedores(String nome, String email ,String telefone, Enderecos endereco, String cnpj) {
        super(nome, email, telefone, endereco);
        this.cnpj = cnpj;
    }
    
    public Fornecedores() {
        
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}

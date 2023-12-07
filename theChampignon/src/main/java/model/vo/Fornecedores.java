package model.vo;

import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedores extends Pessoas{

    private String cnpj;

    public Fornecedores(String nome, String cnpj, String email ,String telefone, Enderecos endereco, Date nascimento){
        super(nome, email, telefone, endereco, nascimento);
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

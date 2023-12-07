package model.vo;

import javax.persistence.Entity;

@Entity
public class Clientes extends Pessoas{
    
    private String cpf;

    public Clientes(String nome, String email, String telefone, Enderecos endereco, String cpf) {
        super(nome, email, telefone, endereco);
        this.cpf = cpf;
    }
    
    public Clientes() {
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

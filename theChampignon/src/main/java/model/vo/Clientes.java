package model.vo;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Clientes extends Pessoas{
    
    public Clientes(String nome, String email, String telefone, Enderecos endereco, String cpf, Date nascimento) {
        super(nome, cpf, email, telefone, endereco, nascimento);
    }
    
    public Clientes() {
    }   
}

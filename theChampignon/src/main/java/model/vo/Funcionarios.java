package model.vo;

import java.util.Date;
import javax.persistence.Entity;


@Entity
public class Funcionarios extends Pessoas{
    
    private float salario;
    private Date admissao;
    private Date demissao;

    public Funcionarios(String nome, String cpf, String email, String telefone, Enderecos endereco, float salario, Date admissao, Date demissao) {
        super(nome, cpf, email, telefone, endereco);
        this.salario = salario;
        this.admissao = admissao;
        this.demissao = demissao;
    }
    
    public Funcionarios() {

    }
    
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Date getDemissao() {
        return demissao;
    }

    public void setDemissao(Date demissao) {
        this.demissao = demissao;
    }
}
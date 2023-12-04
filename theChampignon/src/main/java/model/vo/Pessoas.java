package model.vo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pessoas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
        
    private String nome;
    private String email;
    private String telefone;
    @ManyToOne
    private Enderecos endereco;

    public Pessoas(String nome, String email, String telefone, Enderecos endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
     public Pessoas() {
        
    }
    private int cancelado;
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }

    public Enderecos getEndereco() {
        return endereco;
    }

    public void setEndereco(Enderecos endereco) {
        this.endereco = endereco;
    }
}
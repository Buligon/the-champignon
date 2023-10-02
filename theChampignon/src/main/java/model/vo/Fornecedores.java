package model.vo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fornecedores {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private Long id;
    
    private String cnpj;
    private String razaosocial;
    private String telefone;
    @Embedded
    private Enderecos endereco;
    private int cancelado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Enderecos getEndereco() {
        return endereco;
    }

    public void setEndereco(Enderecos endereco) {
        this.endereco = endereco;
    }

    public int getCancelado() {
        return cancelado;
    }

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }
    
}

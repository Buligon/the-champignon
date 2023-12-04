package model.vo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enderecos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idEndereco;
    
    private String pais;
    private String estado;
    private String cidade;
    private String rua;
    private String logradouro;
    private Integer numero;
    private Integer cep;
   
    public Enderecos(String pais, String estado, String cidade, String rua, String logradouro, Integer numero, Integer cep) {
        
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }
    
    public Enderecos(){
    }
    
    public String getPais(){
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

}
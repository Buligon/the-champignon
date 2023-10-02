package model.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Enderecos {
    private String pais;

    public Enderecos(String pais, String estado, String cidade, String logradouro, Integer numero, String complemento, Integer cep) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }
    private String estado;
    private String cidade;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private Integer cep;
}
package model.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Enderecos {
    private String pais;
    private String estado;
    private String cidade;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private Integer cep;
}
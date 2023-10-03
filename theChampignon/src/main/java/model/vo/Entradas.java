package model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Entradas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Funcionarios funcionario;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Fornecedores fornecedor;
    
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntradaProdutos> produtos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<EntradaProdutos> getProdutos() {
        return produtos;
    }

    public Entradas(Date dataEntrada, Funcionarios funcionario, Fornecedores fornecedor) {
        this.dataEntrada = dataEntrada;
        this.funcionario = funcionario;
        this.fornecedor = fornecedor;
    }
    
    public Entradas() {
        
    }
    
}

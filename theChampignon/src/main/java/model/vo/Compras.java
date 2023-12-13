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

@Entity
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String dataCompra;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Funcionarios funcionario;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Fornecedores fornecedor;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComprasProdutos> produtos = new ArrayList<>();
    
    private int cancelado;

    public void setCancelado(int cancelado) {
        this.cancelado = cancelado;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCompras() {
        return dataCompra;
    }

    public void setDataCompras(String dataCompra) {
        this.dataCompra = dataCompra;
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

    public List<ComprasProdutos> getProdutos() {
        return produtos;
    }

    public Compras(String dataCompra, Funcionarios funcionario, Fornecedores fornecedor) {
        this.dataCompra = dataCompra;
        this.funcionario = funcionario;
        this.fornecedor = fornecedor;
    }
    
    public Compras() {
        
    }
    
}

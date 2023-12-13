package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Fornecedores;
import model.dao.FornecedoresDAOImpl;
import model.vo.Enderecos;
import model.vo.Funcionarios;

public class FornecedoresRN {
    FornecedoresDAOImpl dao = new FornecedoresDAOImpl();
    
    public void adicionarFornecedor(String nome, String cnpj, String email, String telefone, Enderecos endereco) {
        EnderecoRN EnderecoRN = new EnderecoRN();
        
        Fornecedores novofornecedor = new Fornecedores(nome, cnpj, email, telefone, endereco);

        dao.salvar(novofornecedor);
    }
    
    public Field[] listarCamposFornecedores() {
        return dao.listarCampos();
    }
    
    public List<Fornecedores> listarFornecedores() {
        return dao.listarTodos();
    }
    
    public Fornecedores obterFornecedorPorId(long idPessoa) {
        return dao.obterPorId(idPessoa);
    }
    
    public Fornecedores obterFornecedorPorNome(String nome) {
        return dao.obterFornecedorPorNome(nome);
    }
    
    public void editarFornecedor(Fornecedores funcionario) {
        dao.atualizar(funcionario);
    }
    
    public void excluirFornecedor(long idPessoa){
        dao.excluir(idPessoa);
    }
    
    public List<Fornecedores> filtrarFornecedor(String campo, String filtro){
        return dao.filtrar(campo, filtro);
    }
}

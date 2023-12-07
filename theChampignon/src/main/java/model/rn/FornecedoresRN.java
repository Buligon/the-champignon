package model.rn;

import java.util.Date;
import java.util.List;
import model.vo.Fornecedores;
import model.dao.FornecedoresDAOImpl;
import model.vo.Enderecos;

public class FornecedoresRN {
    FornecedoresDAOImpl dao = new FornecedoresDAOImpl();
    
    public void adicionarFornecedor(String nome, String cnpj, String email, String telefone, Enderecos endereco, Date nascimento) {
        EnderecoRN EnderecoRN = new EnderecoRN();
        
        Fornecedores novofornecedor = new Fornecedores(nome, cnpj, email, telefone, endereco, nascimento);

        dao.salvar(novofornecedor);
    }
    
    public List<Fornecedores> listarFornecedores() {
        return dao.listarTodos();
    }
    
    public Fornecedores obterFornecedorPorId(long idPessoa) {
        return dao.obterPorId(idPessoa);
    }
    
    public void editarFornecedor(Fornecedores funcionario) {
        dao.atualizar(funcionario);
    }
    
    public void excluirFornecedor(long idPessoa){
        dao.excluir(idPessoa);
    }
    
}

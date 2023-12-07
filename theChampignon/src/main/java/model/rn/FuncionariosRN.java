package model.rn;

import java.util.Date;
import java.util.List;
import model.vo.Funcionarios;
import model.dao.FuncionariosDAOImpl;
import model.vo.Enderecos;

public class FuncionariosRN {
    FuncionariosDAOImpl dao = new FuncionariosDAOImpl();
    
    public void adicionarFuncionario(String nome, String cpf, String email, String telefone, Enderecos endereco, float salario, Date admissao, Date demissao, Date nascimento) {
        EnderecoRN EnderecoRN = new EnderecoRN();
        
        Funcionarios novofuncionario = new Funcionarios(nome, cpf, email, telefone, endereco, salario, admissao, demissao, nascimento);

        dao.salvar(novofuncionario);
    }
    
    public List<Funcionarios> listarFuncionario() {
        return dao.listarTodos();
    }
    
    public Funcionarios obterFuncionarioPorId(long idPessoa) {
        return dao.obterPorId(idPessoa);
    }
    
    public void editarFuncionario(Funcionarios funcionario) {
        dao.atualizar(funcionario);
    }
    
    public void excluirFuncionario(long idPessoa){
        dao.excluir(idPessoa);
    }
    
}

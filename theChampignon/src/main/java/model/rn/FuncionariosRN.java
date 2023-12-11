package model.rn;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import model.vo.Funcionarios;
import model.dao.FuncionariosDAOImpl;
import model.vo.Enderecos;

public class FuncionariosRN {
    FuncionariosDAOImpl dao = new FuncionariosDAOImpl();
    
    public void adicionarFuncionario(String nome, String cpf, String email, String telefone, Enderecos endereco, float salario, Date admissao, Date demissao) {
        EnderecoRN EnderecoRN = new EnderecoRN();
        
        Funcionarios novofuncionario = new Funcionarios(nome, cpf, email, telefone, endereco, salario, admissao, demissao);

        dao.salvar(novofuncionario);
    }
    
    public Field[] listarCamposFuncionarios() {
        return dao.listarCampos();
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
    
    public List<Funcionarios> filtrarFuncionario(String campo, String filtro){
        return dao.filtrar(campo, filtro);
    }
    
}

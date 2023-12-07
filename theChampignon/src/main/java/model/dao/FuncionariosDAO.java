package model.dao;

import java.util.List;
import model.vo.Funcionarios;

public interface FuncionariosDAO{
    
    void salvar(Funcionarios funcionario);
    void atualizar(Funcionarios funcionario);
    void excluir(long idPessoa);
    List<Funcionarios> listarTodos();
    List<Funcionarios> filtrar();
    Funcionarios obterPorId(long idPessoa);
    
}
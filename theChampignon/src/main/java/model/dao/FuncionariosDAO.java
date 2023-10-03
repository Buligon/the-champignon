package model.dao;

import java.util.List;
import model.vo.Funcionarios;

public interface FuncionariosDAO{
    
    void salvar(Funcionarios funcionario);
    void atualizar(Funcionarios funcionario);
    void excluir(Funcionarios funcionario);
    List<Funcionarios> listarTodos();
    List<Funcionarios> filtrar();
    
}
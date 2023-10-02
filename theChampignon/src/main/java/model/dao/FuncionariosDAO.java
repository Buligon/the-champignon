package model.dao;

import java.util.List;
import model.vo.Funcionario;

public interface FuncionariosDAO{
    
    void salvar(Funcionario funcionario);
    void atualizar(Funcionario funcionario);
    void excluir(Funcionario funcionario);
    List<Funcionario> listarTodos();
    List<Funcionario> filtrar();
    
}
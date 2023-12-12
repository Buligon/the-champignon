package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Funcionarios;

public interface FuncionariosDAO{
    
    void salvar(Funcionarios funcionario);
    void atualizar(Funcionarios funcionario);
    void excluir(long idPessoa);
    List<Funcionarios> listarTodos();
    List<Funcionarios> filtrar(String campo, String filtro);
    Funcionarios obterPorId(long idPessoa);
    Field[] listarCampos();
    public Funcionarios obterFuncionarioPorNome(String nome);
}
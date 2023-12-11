package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Fornecedores;

public interface FornecedoresDAO {
    void salvar(Fornecedores fornecedor);
    void atualizar(Fornecedores fornecedor);
    void excluir(long idPessoa);
    List<Fornecedores> listarTodos();
    List<Fornecedores> filtrar(String campo, String filtro);;
    Fornecedores obterPorId(long idPessoa);
    Field[] listarCampos();
}

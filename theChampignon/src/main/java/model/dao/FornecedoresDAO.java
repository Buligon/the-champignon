package model.dao;

import java.util.List;
import model.vo.Fornecedores;

public interface FornecedoresDAO {
    void salvar(Fornecedores fornecedor);
    void atualizar(Fornecedores fornecedor);
    void excluir(Fornecedores fornecedor);
    List<Fornecedores> listarTodos();
    List<Fornecedores> filtrar();
}

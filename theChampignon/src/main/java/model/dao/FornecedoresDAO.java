package model.dao;

import java.util.List;
import model.vo.Fornecedores;

public interface FornecedoresDAO {
    void salvar(Fornecedores fornecedor);
    void atualizar(Fornecedores fornecedor);
    void excluir(long idPessoa);
    List<Fornecedores> listarTodos();
    List<Fornecedores> filtrar();
    Fornecedores obterPorId(long idPessoa);
}

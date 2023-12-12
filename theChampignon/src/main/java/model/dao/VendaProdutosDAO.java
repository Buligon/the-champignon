package model.dao;

import java.util.List;
import model.vo.VendaProdutos;

public interface VendaProdutosDAO {
    void salvar(VendaProdutos vendaProduto);
    void atualizar(VendaProdutos vendaProduto);
    void excluir(long idVendaProduto);
    List<VendaProdutos> listarTodos();
}

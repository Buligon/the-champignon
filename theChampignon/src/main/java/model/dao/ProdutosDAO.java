package model.dao;

import java.util.List;
import model.vo.Produtos;

public interface ProdutosDAO {
    
    void salvar(Produtos produto);
    void atualizar(Produtos produto);
    void excluir(long idProduto);
    List<Produtos> listarTodos();
    List<Produtos> filtrar();
    Produtos obterPorId(long idProduto);
    
}
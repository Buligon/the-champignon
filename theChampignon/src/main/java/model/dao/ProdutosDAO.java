package model.dao;

import java.util.List;
import model.vo.Produtos;

public interface ProdutosDAO {
    
    void salvar(Produtos produto);
    void atualizar(Produtos produto);
    void excluir(Produtos produto);
    List<Produtos> listarTodos();
    List<Produtos> filtrar();
    
}
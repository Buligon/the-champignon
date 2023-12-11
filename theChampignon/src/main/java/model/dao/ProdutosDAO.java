package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Produtos;

public interface ProdutosDAO {
    
    void salvar(Produtos produto);
    void atualizar(Produtos produto);
    void excluir(long idProduto);
    List<Produtos> listarTodos();
    List<Produtos> filtrar(String campo, String filtro);
    Produtos obterPorId(long idProduto);
    Field[] listarCampos();
}
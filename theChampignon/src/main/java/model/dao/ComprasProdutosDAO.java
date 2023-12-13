package model.dao;

import java.util.List;
import model.vo.ComprasProdutos;

public interface ComprasProdutosDAO {
    public void salvar(ComprasProdutos vendaProduto);
    public void atualizar(ComprasProdutos vendaProduto);
    public void excluir(long idCompraProduto);
    public List<ComprasProdutos> listarTodos();
    public List<ComprasProdutos> retornarProdutos(long idCompra);
}

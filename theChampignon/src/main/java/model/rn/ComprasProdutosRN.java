package model.rn;

import java.util.List;
import model.dao.ComprasProdutosDAOImpl;
import model.vo.Produtos;
import model.vo.ComprasProdutos;

public class ComprasProdutosRN {
    private ComprasProdutosDAOImpl dao = new ComprasProdutosDAOImpl();

    public void adicionarComprasProduto(ComprasProdutos compraProduto) {
        dao.salvar(compraProduto);

        Produtos produto = compraProduto.getProduto();
        int novaQuantidade = produto.getQuantidade() + compraProduto.getQuantidade();
        produto.setQuantidade(novaQuantidade);

        ProdutosRN produtosRN = new ProdutosRN();
        produtosRN.editarProduto(produto);
    }

    public void devolverProdutos(long idCompras) {
        List<ComprasProdutos> compraProdutosList = dao.retornarProdutos(idCompras);

        ProdutosRN produtosRN = new ProdutosRN();

        for (ComprasProdutos compraProduto : compraProdutosList) {
            Produtos produto = compraProduto.getProduto();
            int quantidadeDevolver = compraProduto.getQuantidade();
            produtosRN.adicionarQuantidade(produto.getId(), -quantidadeDevolver);
            dao.excluir(compraProduto.getId());
        }
    }
    
    public List<ComprasProdutos> mostraProdutos(long idCompras) {
        return dao.retornarProdutos(idCompras);
    }
}
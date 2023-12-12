package model.rn;

import model.dao.VendaProdutosDAOImpl;
import model.vo.Produtos;
import model.vo.VendaProdutos;

public class VendaProdutosRN {
    private VendaProdutosDAOImpl dao = new VendaProdutosDAOImpl();

    public void adicionarVendaProduto(VendaProdutos vendaProduto) {
        dao.salvar(vendaProduto);

        Produtos produto = vendaProduto.getProduto();
        int novaQuantidade = produto.getQuantidade() - vendaProduto.getQuantidade();
        produto.setQuantidade(novaQuantidade);

        ProdutosRN produtosRN = new ProdutosRN();
        produtosRN.editarProduto(produto);
    }
}

package model.rn;

import java.util.List;
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

    public void devolverProdutos(long idVenda) {
        List<VendaProdutos> vendaProdutosList = dao.retornarProdutos(idVenda);

        ProdutosRN produtosRN = new ProdutosRN();

        for (VendaProdutos vendaProduto : vendaProdutosList) {
            Produtos produto = vendaProduto.getProduto();
            int quantidadeDevolver = vendaProduto.getQuantidade();
            produtosRN.adicionarQuantidade(produto.getId(), quantidadeDevolver);
            dao.excluir(vendaProduto.getId());
        }
    }
    
    public List<VendaProdutos> mostraProdutos(long idVenda) {
        return dao.retornarProdutos(idVenda);
    }
}
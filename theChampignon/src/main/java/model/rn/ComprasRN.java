package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Fornecedores;
import model.vo.Funcionarios;
import model.vo.ComprasProdutos;
import model.vo.Compras;
import model.dao.ComprasDAOImpl;
import model.vo.Produtos;

public class ComprasRN {

    ComprasDAOImpl dao = new ComprasDAOImpl();

    public int adicionarCompras(String dataCompras, String nomeFuncionario, String nomeCliente, List<Produtos> produtos, List<Float> quantidades) {
        ComprasProdutosRN compraProdutosRN = new ComprasProdutosRN();
        FuncionariosRN funcionariosRN = new FuncionariosRN();
        FornecedoresRN fornecedoresRN = new FornecedoresRN();
        Compras novaCompras = new Compras();
        novaCompras.setDataCompras(dataCompras);

        Funcionarios funcionario = funcionariosRN.obterFuncionarioPorNome(nomeFuncionario);
        novaCompras.setFuncionario(funcionario);

        Fornecedores fornecedor = fornecedoresRN.obterFornecedorPorNome(nomeCliente);
        novaCompras.setFornecedor(fornecedor);

        dao.salvar(novaCompras);

        for (int i = 0; i < produtos.size(); i++) {
            Produtos produto = produtos.get(i);
            Float quantidade = quantidades.get(i);

            float preco = produto.getValor();

            ComprasProdutos compraProduto = new ComprasProdutos(novaCompras, produto, preco, quantidade.intValue());
            compraProdutosRN.adicionarComprasProduto(compraProduto);
        }

        return 1;
    }

    public float calcularValorTotal(Compras compra) {
        ComprasProdutosRN compraProdutosRN = new ComprasProdutosRN();
        float total = 0.0f;

        List<ComprasProdutos> compraProdutosList = compraProdutosRN.mostraProdutos(compra.getId());

        for (ComprasProdutos compraProduto : compraProdutosList) {
            float precoProduto = compraProduto.getPreco();
            int quantidadeProduto = compraProduto.getQuantidade();
            total += precoProduto * quantidadeProduto;
        }

        return total;
    }

    public List<Compras> listarCompras() {
        return dao.listarTodos();
    }

    public void editarCompras(Compras compra) {
        dao.atualizar(compra);
    }

    public void excluirCompras(long id) {
        ComprasProdutosRN compraProdutosRN = new ComprasProdutosRN();
        compraProdutosRN.devolverProdutos(id);
        dao.excluir(id);
    }

    public List<Compras> filtrarCompras(String campo, String filtro) {
        return dao.filtrar(campo, filtro);
    }

    public Field[] listarCamposCompras() {
        return dao.listarCampos();
    }
}

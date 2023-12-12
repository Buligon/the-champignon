package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Clientes;
import model.vo.Funcionarios;
import model.vo.VendaProdutos;
import model.vo.Vendas;
import model.dao.VendasDAOImpl;
import model.vo.Produtos;

public class VendasRN {

    VendasDAOImpl dao = new VendasDAOImpl();

    public int adicionarVenda(String dataVenda, String nomeFuncionario, String nomeCliente, List<Produtos> produtos, List<Float> quantidades) {
        VendaProdutosRN vendaProdutosRN = new VendaProdutosRN();
        FuncionariosRN funcionariosRN = new FuncionariosRN();
        ClientesRN clientesRN = new ClientesRN();
        Vendas novaVenda = new Vendas();
        novaVenda.setDataVenda(dataVenda);

        Funcionarios funcionario = funcionariosRN.obterFuncionarioPorNome(nomeFuncionario);
        novaVenda.setFuncionario(funcionario);

        Clientes cliente = clientesRN.obterClientePorNome(nomeCliente);
        novaVenda.setCliente(cliente);

        dao.salvar(novaVenda);

        for (int i = 0; i < produtos.size(); i++) {
            Produtos produto = produtos.get(i);
            Float quantidade = quantidades.get(i);

            float preco = produto.getValor();

            VendaProdutos vendaProduto = new VendaProdutos(novaVenda, produto, preco, quantidade.intValue());
            vendaProdutosRN.adicionarVendaProduto(vendaProduto);
        }

        return 1;
    }

    public float calcularValorTotal(Vendas venda) {
        VendaProdutosRN vendaProdutosRN = new VendaProdutosRN();
        float total = 0.0f;

        List<VendaProdutos> vendaProdutosList = vendaProdutosRN.mostraProdutos(venda.getId());

        for (VendaProdutos vendaProduto : vendaProdutosList) {
            float precoProduto = vendaProduto.getPreco();
            int quantidadeProduto = vendaProduto.getQuantidade();
            total += precoProduto * quantidadeProduto;
        }

        return total;
    }

    public List<Vendas> listarVendas() {
        return dao.listarTodos();
    }

    public void editarVenda(Vendas venda) {
        dao.atualizar(venda);
    }

    public void excluirVenda(long id) {
        VendaProdutosRN vendaProdutosRN = new VendaProdutosRN();
        vendaProdutosRN.devolverProdutos(id);
        dao.excluir(id);
    }

    public List<Vendas> filtrarVenda(String campo, String filtro) {
        return dao.filtrar(campo, filtro);
    }

    public Field[] listarCamposVendas() {
        return dao.listarCampos();
    }
}

package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Produtos;
import model.dao.ProdutosDAOImpl;
import model.vo.Especies;
import model.vo.Unidades;

public class ProdutosRN {
    ProdutosDAOImpl dao = new ProdutosDAOImpl();
    
    public void adicionarProduto(String descricao, float custo, float valor, String unidadeSelecionada, String especieSelecionada) {
        EspeciesRN especiesRN = new EspeciesRN();
        UnidadesRN unidadesRN = new UnidadesRN();

        Especies especie = especiesRN.obterEspeciePelaDescricao(especieSelecionada);
        Unidades unidade = unidadesRN.obterUnidadePelaDescricao(unidadeSelecionada);

        Produtos novoProduto = new Produtos(descricao, especie, unidade, custo, valor);

        dao.salvar(novoProduto);
    }
    
    public Field[] listarCamposProdutos() {
        return dao.listarCampos();
    }
    
    public List<Produtos> listarProdutos() {
        return dao.listarTodos();
    }
    
    public Produtos obterProdutoPorId(long idProduto) {
        return dao.obterPorId(idProduto);
    }
    
    public void editarProduto(Produtos produto) {
        dao.atualizar(produto);
    }
    
    public void excluirProduto(long idProduto){
        dao.excluir(idProduto);
    }
    
        public List<Produtos> filtrarProduto(String campo, String filtro){
        return dao.filtrar(campo, filtro);
    }
        
    public void adicionarQuantidade(long idProduto, int quantidade) {
        try {
            Produtos produto = dao.obterPorId(idProduto);
            if (produto != null) {
                int novaQuantidade = produto.getQuantidade() + quantidade;
                produto.setQuantidade(novaQuantidade);
                dao.atualizar(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removerQuantidade(long idProduto, int quantidade) {
        try {
            Produtos produto = dao.obterPorId(idProduto);
            if (produto != null) {
                int novaQuantidade = produto.getQuantidade() + quantidade;
                produto.setQuantidade(novaQuantidade);
                dao.atualizar(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

package model.dao;

import java.util.List;
import model.vo.Vendas;

public interface VendasDAO {
    void salvar(Vendas venda);
    void atualizar(Vendas venda);
    //void excluir(Vendas venda);
    List<Vendas> listarTodos();
    List<Vendas> filtrar();
}

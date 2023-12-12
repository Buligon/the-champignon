package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Vendas;

public interface VendasDAO {
    void salvar(Vendas venda);
    void atualizar(Vendas venda);
    void excluir(long idVenda);
    List<Vendas> listarTodos();
    List<Vendas> filtrar(String campo, String filtro);
    public Field[] listarCampos();
}

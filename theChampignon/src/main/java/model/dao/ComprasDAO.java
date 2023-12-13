package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Compras;

public interface ComprasDAO {
    void salvar(Compras compra);
    void atualizar(Compras compra);
    void excluir(long idVenda);
    List<Compras> listarTodos();
    List<Compras> filtrar(String campo, String filtro);
    public Field[] listarCampos();
}

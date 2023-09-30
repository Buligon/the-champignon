package model.dao;

import java.util.List;
import model.vo.Unidades;

public interface UnidadesDAO {
    void salvar(Unidades unidade);
    void atualizar(Unidades unidade);
    void excluir(Unidades unidade);
    List<Unidades> listarTodos();
    List<Unidades> filtrar();
}
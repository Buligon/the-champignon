package model.dao;

import java.util.List;
import model.vo.Entradas;

public interface EntradasDAO {
    void salvar(Entradas entrada);
    void atualizar(Entradas entrada);
    //void excluir(Entradas entrada);
    List<Entradas> listarTodos();
    List<Entradas> filtrar();
}

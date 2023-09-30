package model.dao;

import java.util.List;
import model.vo.Especies;

public interface EspeciesDAO {
    void salvar(Especies especie);
    void atualizar(Especies especie);
    void excluir(Especies especie);
    List<Especies> listarTodos();
    List<Especies> filtrar();
}
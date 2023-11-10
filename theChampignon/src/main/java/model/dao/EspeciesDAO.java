package model.dao;

import java.util.List;
import model.vo.Especies;

public interface EspeciesDAO {
    void salvar(Especies especie);
    void atualizar(long idEspecie, String descricaoNova);
    void excluir(long idEspecie);
    List<Especies> listarTodos();
    List<Especies> filtrar();
}
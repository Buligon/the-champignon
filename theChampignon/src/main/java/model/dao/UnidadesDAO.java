package model.dao;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Unidades;

public interface UnidadesDAO {
    void salvar(Unidades unidade);
    void atualizar(long idUnidade, String descricaoNova);
    void excluir(long idUnidade);
    List<Unidades> listarTodos();
    List<Unidades> filtrar(String campo, String filtro);
    Field[] listarCampos();
}
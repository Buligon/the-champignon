package model.dao;

import java.util.List;
import model.vo.Pessoas;

public interface PessoasDAO {
    void salvar(Pessoas pessoa);
    void atualizar(Pessoas pessoa);
    void excluir(Pessoas pessoa);
    List<Pessoas> listarTodos();
    List<Pessoas> filtrar();
}

package model.dao;

import model.vo.Cliente;
import java.util.List;

public interface ClientesDAO {
    void salvar(Cliente cliente);
    void atualizar(Cliente cliente);
    void excluir(Cliente cliente);
    void excluir(Long id);
    List<Cliente> listarTodos();
    Cliente listarUm(Long id);
}

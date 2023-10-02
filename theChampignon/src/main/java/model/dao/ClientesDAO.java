package model.dao;

import model.vo.Clientes;
import java.util.List;

public interface ClientesDAO {
    void salvar(Clientes cliente);
    void atualizar(Clientes cliente);
    void excluir(Clientes cliente);
    List<Clientes> listarTodos();
    List<Clientes> filtrar();
}

package model.rn;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import model.vo.Clientes;
import model.vo.Enderecos;
import model.dao.ClientesDAOImpl;

public class ClientesRN {
    ClientesDAOImpl dao = new ClientesDAOImpl();
    
    public void adicionarCliente(String nome, String email, String telefone, Enderecos endereco, String cpf) {
        
        EnderecoRN EnderecoRN = new EnderecoRN();
        
        Clientes novocliente = new Clientes(nome, email, telefone, endereco, cpf);

        dao.salvar(novocliente);
    }
    
    public Field[] listarCamposClientes() {
        return dao.listarCampos();
    }
    
    public List<Clientes> listarClientes() {
        return dao.listarTodos();
    }
    
    public Clientes obterClientePorId(long idPessoa) {
        return dao.obterPorId(idPessoa);
    }
    
    public void editarCliente(Clientes cliente) {
        dao.atualizar(cliente);
    }
    
    public void excluirCliente(long idPessoa){
        dao.excluir(idPessoa);
    }
    
    public Clientes obterClientePorNome(String nome) {
        return dao.obterClientePorNome(nome);
    }
    
    public List<Clientes> filtrarCliente(String campo, String filtro){
        return dao.filtrar(campo, filtro);
    }
}

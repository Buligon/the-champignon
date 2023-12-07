package model.rn;

import java.util.Date;
import java.util.List;
import model.vo.Clientes;
import model.vo.Enderecos;
import model.dao.ClientesDAOImpl;

public class ClientesRN {
    ClientesDAOImpl dao = new ClientesDAOImpl();
    
    public void adicionarCliente(String nome, String email, String telefone, Enderecos endereco, String cpf, Date nascimento) {
        
        EnderecoRN EnderecoRN = new EnderecoRN();
        
        Clientes novocliente = new Clientes(nome, email, telefone, endereco, cpf, nascimento);

        dao.salvar(novocliente);
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
    
}

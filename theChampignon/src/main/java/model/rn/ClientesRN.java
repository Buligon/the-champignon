package model.rn;

import java.util.List;
import model.vo.Clientes;
import model.vo.Enderecos;
import model.dao.ClientesDAOImpl;

public class ClientesRN {
    ClientesDAOImpl dao = new ClientesDAOImpl();
    
    public void adicionarCliente(String nome, String cpf, String email, String telefone, Enderecos endereco ) {
        
        //Ajustar futuramente
        //EnderecoRN EnerecoRN = new EnerecoRN();

        //Enderecos endereco = EnderecoRN.obterEspeciePelaDescricao(especieSelecionada);
        
        //Funcionalidade aplicada futuramente, por isso só deixei desta forma para que seja preenchido        
        Clientes novocliente = new Clientes(nome, email, telefone, endereco, cpf);

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

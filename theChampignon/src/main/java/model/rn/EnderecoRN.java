package model.rn;

import java.util.List;
import model.vo.Enderecos;
import model.dao.EnderecosDAOImpl;

public class EnderecoRN{
    EnderecosDAOImpl dao = new EnderecosDAOImpl();
    
    public Enderecos adicionarEndereco(String pais, String estado, String cidade, String rua, String logradouro, Integer numero, Integer cep) {
        
        Enderecos novoEndereco = new Enderecos(pais, estado, cidade, rua, logradouro, numero, cep);
        dao.salvar(novoEndereco);
        return novoEndereco;                            
    }
    
    public Enderecos obterEnderecoPorId(long idEndereco) {
        return dao.obterPorId(idEndereco);
    }
    
    public void editarEndereco(Enderecos endereco) {
        dao.atualizar(endereco);
    }
    
    public void excluirEndereco(long idEndereco){
        dao.excluir(idEndereco);
    }
}

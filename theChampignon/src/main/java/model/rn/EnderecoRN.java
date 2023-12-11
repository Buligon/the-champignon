package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Enderecos;
import model.dao.EnderecosDAOImpl;

public class EnderecoRN{
    EnderecosDAOImpl dao = new EnderecosDAOImpl();
    
    public Enderecos adicionarEndereco(String pais, String estado, String cidade, String rua, String logradouro, Integer numero, String cep, String bairro) {
        
        Enderecos novoEndereco = new Enderecos(pais, estado, cidade, rua, logradouro, numero, cep, bairro);
        dao.salvar(novoEndereco);
        return novoEndereco;                            
    }
    
    public Enderecos obterEnderecoPorId(long idEndereco) {
        return dao.obterPorId(idEndereco);
    }
    
    public Field[] listarCamposEndereco() {
        return dao.listarCampos();
    }
    
    public void editarEndereco(Enderecos endereco) {
        dao.atualizar(endereco);
    }
    
    public void excluirEndereco(long idEndereco){
        dao.excluir(idEndereco);
    }
}

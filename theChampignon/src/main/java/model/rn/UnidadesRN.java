package model.rn;

import java.util.List;
import model.vo.Unidades;
import model.dao.UnidadesDAOImpl;

public class UnidadesRN {
    UnidadesDAOImpl dao = new UnidadesDAOImpl();
    
    public void adicionarUnidade(String descricao) {
        Unidades novaUnidade = new Unidades(descricao);

        dao.salvar(novaUnidade);
    }
    
    public void editarUnidade(long idUnidade, String descricaoNova) {
        dao.atualizar(idUnidade, descricaoNova);
    }
    
    public void excluirUnidade(long idUnidade) {
        dao.excluir(idUnidade);
    }
    
    public List<Unidades> listarUnidades() {
        return dao.listarTodos();
    }
}

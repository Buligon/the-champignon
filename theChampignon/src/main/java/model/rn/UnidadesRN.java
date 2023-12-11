package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Unidades;
import model.dao.UnidadesDAOImpl;

public class UnidadesRN {
    UnidadesDAOImpl dao = new UnidadesDAOImpl();
    
    public void adicionarUnidade(String descricao) {
        Unidades novaUnidade = new Unidades(descricao);

        dao.salvar(novaUnidade);
    }
    
    public Field[] listarCamposUnidade() {
        return dao.listarCampos();
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
    
    public Unidades obterUnidadePelaDescricao(String descricao) {
        List<Unidades> unidades = dao.listarTodos();

        for (Unidades unidade : unidades) {
            if (unidade.getDescricao().equals(descricao)) {
                return unidade;
            }
        }

        return null;
    }
    
    public List<Unidades> filtrarUnidade(String campo, String filtro){
        return dao.filtrar(campo, filtro);
    }
}

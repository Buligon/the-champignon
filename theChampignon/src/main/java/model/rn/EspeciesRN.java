package model.rn;

import java.lang.reflect.Field;
import java.util.List;
import model.vo.Especies;
import model.dao.EspeciesDAOImpl;

public class EspeciesRN {
    EspeciesDAOImpl dao = new EspeciesDAOImpl();
    
    public void adicionarEspecie(String descricao) {
        Especies novaEspecie = new Especies(descricao);

        dao.salvar(novaEspecie);
    }
    
    public Field[] listarCamposEspecies() {
        return dao.listarCampos();
    }
    
    public void editarEspecie(long idEspecie, String descricaoNova) {
        dao.atualizar(idEspecie, descricaoNova);
    }
    
    public void excluirEspecie(long idEspecie) {
        dao.excluir(idEspecie);
    }
    
    public List<Especies> listarEspecies() {
        return dao.listarTodos();
    }
    
    public Especies obterEspeciePelaDescricao(String descricao) {
        List<Especies> especies = dao.listarTodos();

        for (Especies especie : especies) {
            if (especie.getDescricao().equals(descricao)) {
                return especie;
            }
        }

        return null;
    }
    
    public List<Especies> filtrarEspecie(String campo, String filtro){
        return dao.filtrar(campo, filtro);
    }
}

package model.dao;

import model.vo.Enderecos;

public interface EnderecoDAO {
    void salvar(Enderecos endereco);
    void atualizar(Enderecos endereco);
    void excluir(long idEndereco);
    Enderecos obterPorId(long idEndereco);

}

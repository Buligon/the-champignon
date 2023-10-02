package model.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Clientes extends Pessoas {
    /*@OneToMany(mappedBy = "cliente")
    List<Venda> vendas;*/
}

package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
//@Builder
@NoArgsConstructor
public class Inventario {

    private Integer arma;
    private Integer municao;
    private Integer agua;
    private Integer comida;

    public Integer getArma() {
        return this.arma;
    }

    public Integer getMunicao() {
        return this.municao;
    }

    public Integer getAgua() {
        return this.agua;
    }

    public Integer getComida() {
        return this.comida;
    }


}


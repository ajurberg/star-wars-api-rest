package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Builder
@NoArgsConstructor
public class Inventario {

    private Integer arma;
    private Integer municao;
    private Integer agua;
    private Integer comida;

}

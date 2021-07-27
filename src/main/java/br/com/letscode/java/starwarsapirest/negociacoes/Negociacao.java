package br.com.letscode.java.starwarsapirest.negociacoes;

import br.com.letscode.java.starwarsapirest.rebeldes.Inventario;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Negociacao {

    private Integer idRebelde1;
    private Inventario inventarioRebelde1;
    private Integer idRebelde2;
    private Inventario inventarioRebelde2;

}

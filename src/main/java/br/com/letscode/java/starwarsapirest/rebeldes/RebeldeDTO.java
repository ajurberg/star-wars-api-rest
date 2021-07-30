package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class RebeldeDTO {

    private Integer idRebelde;
    private Localizacao localizacao;
}

package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Localizacao {

    private Long latitude;
    private Long longitude;
    private String nomeGalaxia;
    private String nomeBase;

}

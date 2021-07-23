package br.com.letscode.java.starwarsapirest.rebeldes;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Rebelde {

    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private Localizacao localizacao;

}

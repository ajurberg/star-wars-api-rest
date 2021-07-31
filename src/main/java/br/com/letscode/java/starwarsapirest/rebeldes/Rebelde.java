package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rebelde {

    private Integer idRebelde;
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private Integer downgrade;
    private Localizacao localizacao;
    private Inventario inventario;

}

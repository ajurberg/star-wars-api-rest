package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class RebeldeDTO {

    private Integer idRebelde;
    private Localizacao localizacao;

}

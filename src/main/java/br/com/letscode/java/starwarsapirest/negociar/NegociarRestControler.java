package br.com.letscode.java.starwarsapirest.negociar;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/negociar")
@RestController
@AllArgsConstructor
public class NegociarRestControler {

    private final NegociarService negociarService;

    @PostMapping
    private String negociar(@RequestBody Negociacao negociacao){

        return negociarService.negociar(negociacao);


    }


}

package br.com.letscode.java.starwarsapirest.negociacoes;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/negociacao")
@RestController
@AllArgsConstructor
public class NegociacaoRestController {

    private final NegociacaoService negociacaoService;

    @PostMapping
    private String negociar(@RequestBody Negociacao negociacao){
        return negociacaoService.negociar(negociacao);
    }

}

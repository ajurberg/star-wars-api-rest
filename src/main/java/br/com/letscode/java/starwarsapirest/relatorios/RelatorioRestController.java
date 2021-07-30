package br.com.letscode.java.starwarsapirest.relatorios;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/relatorio")
@RestController
@AllArgsConstructor
public class RelatorioRestController {

    private final RelatoriosService relatoriosService;

    @GetMapping
    public String exibirRelatorios() throws IOException {
        return relatoriosService.relatorioRebeldes() + "\n" +
                relatoriosService.relatorioRecursosRebeldes() + "\n" +
                relatoriosService.relatorioRecursosTraidores();
    }
}
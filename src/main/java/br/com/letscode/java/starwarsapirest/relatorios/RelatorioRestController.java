package br.com.letscode.java.starwarsapirest.relatorios;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class RelatorioRestController {

    private final RelatoriosService relatoriosService;

    @GetMapping("/relatorio1")
    public String relatorioTraidoresRebeldes() throws IOException {
        return relatoriosService.relatorio1();
    }

}

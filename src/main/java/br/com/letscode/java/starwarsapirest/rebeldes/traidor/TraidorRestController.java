package br.com.letscode.java.starwarsapirest.rebeldes.traidor;

import br.com.letscode.java.starwarsapirest.rebeldes.RebeldesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/traidores")
@RestController
@AllArgsConstructor
public class TraidorRestController {

    private final TraidorService traidorService;

    @PostMapping()
    private String reportarTraidor(@RequestBody Integer traidorId) throws IOException {
        return traidorService.reportarTraidor(traidorId);
    }
}

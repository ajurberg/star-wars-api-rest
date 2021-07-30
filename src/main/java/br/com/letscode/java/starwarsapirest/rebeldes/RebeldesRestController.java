package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/rebeldes")
@RestController
@AllArgsConstructor
public class RebeldesRestController {

    private final RebeldesService rebeldesService;

    @GetMapping
    public List listarRebeldes() throws IOException {
        return rebeldesService.listRebeldes();
    }

    @PostMapping
    private String adicionarRebelde(@RequestBody Rebelde rebelde) throws IOException {
        return rebeldesService.addRebeldeService(rebelde);
    }

    @PutMapping
    private String atualizarRebelde(@RequestBody RebeldeDTO rebeldeDTO) throws IOException {
        return rebeldesService.updateLocationRebeldeService(rebeldeDTO);
    }
}

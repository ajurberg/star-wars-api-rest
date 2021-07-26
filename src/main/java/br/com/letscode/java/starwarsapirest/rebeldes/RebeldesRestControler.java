package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequestMapping("/rebeldes")
@RestController
@AllArgsConstructor
public class RebeldesRestControler {

    private final RebeldesService rebeldesService;

    @GetMapping
    public List createQuiz() throws IOException {
        return rebeldesService.listAll();
    }

    @PostMapping
    private String addRebelde(@RequestBody Rebelde rebelde){
        return rebeldesService.adRebeldeService(rebelde);
    }

}

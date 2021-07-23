package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/rebeldes")
@RestController
@AllArgsConstructor
public class RebeldesRestControler {

    private final RebeldesService rebeldesService;


    @PostMapping
    private String adRebelde (@RequestBody Rebelde rebelde){

        return rebeldesService.adRebeldeService(rebelde);

    }

}

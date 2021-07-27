package br.com.letscode.java.starwarsapirest.relatorios;

import br.com.letscode.java.starwarsapirest.rebeldes.Rebelde;
import br.com.letscode.java.starwarsapirest.rebeldes.RebeldesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class RelatoriosService {

    private final RebeldesRepository rebeldesRepository;

    public String relatorio1() throws IOException {
        Double traidoresCont = 0d;
        Double rebeldesCont = 0d;
        List<Rebelde> rebeldes = rebeldesRepository.getAll();
        for (Rebelde rebelde : rebeldes) {
            rebeldesCont++;
            if ( rebelde.getDowngrade() >= 3 ) {
                traidoresCont++;
            }
        }

        return "O total de rebeldes é: " + (rebeldesCont - traidoresCont) + "\n" +
                "O total de traidores é: " +  traidoresCont + "\n" +
                "O percentual de traidores é: " + (traidoresCont/rebeldesCont) *100 + "%\n" +
                "O percentual de total rebeldes é: " + (1 - (traidoresCont/rebeldesCont)) *100 + "%"
                ;
    }
}

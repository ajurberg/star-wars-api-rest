package br.com.letscode.java.starwarsapirest.rebeldes.traidor;

import br.com.letscode.java.starwarsapirest.rebeldes.Rebelde;
import br.com.letscode.java.starwarsapirest.rebeldes.RebeldesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class TraidorService {

    private final RebeldesRepository rebeldesRepository;

    public String reportarTraidor(Integer traidorID) throws IOException {
        List<Rebelde> lista = rebeldesRepository.getAll();
        for (Rebelde traidor : lista) {
            if (traidorID.equals(traidor.getIdRebelde())) {
                traidor.setDowngrade(traidor.getDowngrade() + 1);
                this.rebeldesRepository.atualizarNoArquivo(traidor);
                return "Traidor " + traidor.getNome() + " foi reportado com sucesso";
            }
        }
        return "Rebelde não encontrado";
    }
}

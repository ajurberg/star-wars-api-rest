package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class RebeldesService {

    private final RebeldesRepository rebeldesRepository;


    public String adRebeldeService(Rebelde rebelde) {
        String retorno = "";

        if (rebelde.getGenero() == null
                || rebelde.getIdade() == null || rebelde.getNome() == null) {
            retorno = "Por favor, preencha todos os campos";
        } else {

            retorno = ">>>>>Rebelde ID: " + rebelde.getIdRebelde() + " Nome: " + rebelde.getNome() + ", cadastrado com sucesso!<<<<< \n" +
                    "Idade:" + rebelde.getIdade() + "\nGenero:" + rebelde.getGenero() +
                    "\n" + rebelde.getLocalizacao() +
                    "\n" + rebelde.getInventario();
        }
        rebeldesRepository.inserirNoArquivo(rebelde);
        return retorno;
    }

    public List listAll() throws IOException {
       return rebeldesRepository.getAll();
    }
}

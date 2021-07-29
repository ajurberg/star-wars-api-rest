package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RebeldesService {

    private final RebeldesRepository rebeldesRepository;

    public List listRebeldes() throws IOException {
        List<Rebelde> todos = rebeldesRepository.getAll();
        List<Rebelde> rebeldes = new ArrayList<>();
        for (Rebelde rebelde : todos) {
            if (rebelde.getDowngrade() < 3) {
                rebeldes.add(rebelde);
            }
        }
        return rebeldes;
    }

    public String addRebeldeService(Rebelde rebelde) {
        String retorno = "";
        if (rebelde.getGenero() == null
                || rebelde.getIdade() == null || rebelde.getNome() == null) {
            retorno = "Por favor, preencha todos os campos"; // TODO Include exception
        } else {
            retorno = ">>>>> Rebelde ID: " + rebelde.getIdRebelde() + " Nome: " + rebelde.getNome() + ", cadastrado com sucesso! <<<<< \n" +
                    "Idade: " + rebelde.getIdade() +
                    "\nGenero: " + rebelde.getGenero() +
                    "\nLocalização: " + rebelde.getLocalizacao() +
                    "\nInventário: " + rebelde.getInventario();
            rebeldesRepository.inserirNoArquivo(rebelde);
        }
        return retorno;
    }

    public String updateLocationRebeldeService(RebeldeDTO rebeldeDTO) throws IOException {
        var rebeldeAtualizado = findByIdRebelde(rebeldeDTO.getIdRebelde());
        String atualizacao = "";
        if (rebeldeAtualizado != null) {
            rebeldeAtualizado.setLocalizacao(rebeldeDTO.getLocalizacao());
            atualizacao = ">>>>> Rebelde ID: " + rebeldeDTO.getIdRebelde() + " Nome: " + rebeldeAtualizado.getNome() + ", atualizado com sucesso! <<<<< \n" +
                    "\n Nova localização: " + rebeldeAtualizado.getLocalizacao();
            rebeldesRepository.atualizarNoArquivo(rebeldeAtualizado);
        } else {
            atualizacao = ">>>>> Rebelde não encontrado. <<<<< \n";
        }
        return atualizacao;
    }

    public Rebelde findByIdRebelde(Integer idRebelde) throws IOException {
        List<Rebelde> rebeldes = listRebeldes();
        Optional<Rebelde> optionalRebelde = rebeldes.stream()
                .filter(buscaRebelde -> buscaRebelde.getIdRebelde().equals(idRebelde)).findFirst();
        if (optionalRebelde.isPresent()) {
            return optionalRebelde.get();
        } else {
            return null;
        }
    }

    public String reportarTraidor(Integer traidorID) throws IOException {
        List<Rebelde> lista = rebeldesRepository.getAll();
        for (Rebelde rebelde : lista) {
            if (traidorID.equals(rebelde.getIdRebelde())) {
                rebelde.setDowngrade(rebelde.getDowngrade() + 1);
                this.rebeldesRepository.atualizarNoArquivo(rebelde);
                return "Traidor "+ rebelde.getNome() + " foi reportado com sucesso";
            }

        }
        return "Rebelde não encontrado";
    }

}

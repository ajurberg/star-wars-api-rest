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

    public String addRebeldeService(Rebelde rebelde) throws IOException {
        if (findAllById(rebelde.getIdRebelde()) == null) {
            if (rebelde.getGenero() == null
                    || rebelde.getIdade() == null || rebelde.getNome() == null) {
                return "Por favor, preencha todos os campos";
            } else {
                rebeldesRepository.inserirNoArquivo(rebelde);
                return ">>>>> Rebelde ID: " + rebelde.getIdRebelde() + " Nome: " +
                        rebelde.getNome() + ", cadastrado com sucesso! <<<<< \n" +
                        "Idade: " + rebelde.getIdade() +
                        "\nGenero: " + rebelde.getGenero() +
                        "\nLocalização: " + rebelde.getLocalizacao() +
                        "\nInventário: " + rebelde.getInventario();
            }
        }
        return "Usúario já existente";
    }

    public String updateLocationRebeldeService(RebeldeDTO rebeldeDTO) throws IOException {
        var rebeldeAtualizado = findByIdRebelde(rebeldeDTO.getIdRebelde());
        String atualizacao = "";
        if (rebeldeAtualizado != null) {
            rebeldeAtualizado.setLocalizacao(rebeldeDTO.getLocalizacao());
            atualizacao = ">>>>> Rebelde ID: " + rebeldeDTO.getIdRebelde() + " Nome: "
                    + rebeldeAtualizado.getNome() + ", atualizado com sucesso! <<<<< \n" +
                    "\n Nova localização: " + rebeldeAtualizado.getLocalizacao();
            rebeldesRepository.atualizarNoArquivo(rebeldeAtualizado);
        } else {
            atualizacao = ">>>>> Rebelde não encontrado. <<<<< \n";
        }
        return atualizacao;
    }

    public Rebelde findByIdRebelde(Integer idRebelde) throws IOException {
        //Não aderir a sugestão do Intellij, pois quebra o código.
        List<Rebelde> rebeldes = listRebeldes();
        Optional<Rebelde> optionalRebelde = rebeldes.stream()
                .filter(buscaRebelde -> buscaRebelde.getIdRebelde().equals(idRebelde)).findFirst();
        return optionalRebelde.orElse(null);
    }

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

    public Rebelde findAllById(Integer idRebelde) throws IOException {
        List<Rebelde> rebeldes = rebeldesRepository.getAll();
        Optional<Rebelde> optionalRebelde = rebeldes.stream()
                .filter(buscaRebelde -> buscaRebelde.getIdRebelde().equals(idRebelde)).findFirst();
        return optionalRebelde.orElse(null);
    }
}

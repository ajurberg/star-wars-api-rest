package br.com.letscode.java.starwarsapirest.rebeldes;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RebeldesService {

    private final RebeldesRepository rebeldesRepository;

    public List listAll() throws IOException {
        return rebeldesRepository.getAll();
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
        List<Rebelde> rebeldes = listAll();
        Optional<Rebelde> optionalRebelde = rebeldes.stream()
                .filter(buscaRebelde -> buscaRebelde.getIdRebelde().equals(idRebelde)).findFirst();
        if (optionalRebelde.isPresent()) {
            return optionalRebelde.get();
        } else {
            return null;
        }
    }

//    public void reportTreason(Rebelde rebelde) throws IOException {
//        if (listAll().contains(rebelde)) {
//            rebelde.setDowngrade(rebelde.getDowngrade() + 1);
//        }
//    }

    public void adicionarItemNoInventario(Rebelde rebelde, Inventario item) throws IOException {
        if (listAll().contains(rebelde)) {
            if (rebelde.getDowngrade() >= 3) {
                // TODO
            } else {
                // TODO
            }
        }
    }


    public void removerItemNoInventario(Rebelde rebelde, Inventario item) throws IOException {
        if (listAll().contains(rebelde)) {

        }
    }

}

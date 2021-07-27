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

    public String relatorioRebeldes() throws IOException {
        Double traidoresConter = 0d;
        Double rebeldesConter = 0d;
        List<Rebelde> rebeldes = rebeldesRepository.getAll();
        for (Rebelde rebelde : rebeldes) {
            rebeldesConter++;
            if (rebelde.getDowngrade() >= 3) {
                traidoresConter++;
            }
        }
        return ">>>>> RELATÓRIO DE PESSOAL <<<<< \n" +
                "O total de rebeldes é: " + (rebeldesConter - traidoresConter) + "\n" +
                "O total de traidores é: " + traidoresConter + "\n" +
                "O percentual de traidores é: " + (traidoresConter / rebeldesConter) * 100 + "%\n" +
                "O percentual de total rebeldes é: " + (1 - (traidoresConter / rebeldesConter)) * 100 + "%\n" +
                "\n";
    }

    public String relatorioRecursosRebeldes() throws IOException {
        int armaCounter = 0;
        int municaoCounter = 0;
        int aguaCounter = 0;
        int comidaCounter = 0;
        int rebeldeCounter = 0;
        List<Rebelde> rebeldes = rebeldesRepository.getAll();
        for (Rebelde rebelde : rebeldes) {
            // Rebeldes
            if (rebelde.getDowngrade() < 3) {
                armaCounter += rebelde.getInventario().getArma();
                municaoCounter = rebelde.getInventario().getMunicao();
                aguaCounter = rebelde.getInventario().getAgua();
                comidaCounter = rebelde.getInventario().getComida();
                rebeldeCounter++;
            }
        }
        int pontosRebeldes = (armaCounter * 4) + (municaoCounter * 3) + (aguaCounter * 2) + comidaCounter;
        return ">>>>> RELATÓRIO DE REBELDES <<<<< \n" +
                "O número total de traidores é: " + rebeldeCounter + "\n" +
                "O total de armas dos Rebeldes é: " + armaCounter + "\n" +
                "O total de munição dos Rebeldes é: " + municaoCounter + "\n" +
                "O total de água dos Rebeldes é: " + aguaCounter + "\n" +
                "O total de comida dos Rebeldes é: " + comidaCounter + "\n" +
                "\n" +
                ">>>>> MÉDIA DE RECURSOS POR REBELDE <<<<< \n" +
                "A média de armas por rebelde é: " + armaCounter / rebeldeCounter + "\n" +
                "A média de munição por rebelde é: " + municaoCounter / rebeldeCounter + "\n" +
                "A média de água por rebelde é: " + aguaCounter / rebeldeCounter + "\n" +
                "A média de comida por rebelde é: " + comidaCounter / rebeldeCounter + "\n" +
                "\n" +
                ">>>>> PONTUAÇÃO TOTAL DOS REBELDES <<<<< \n" +
                "Os Rebeldes têm " + pontosRebeldes + " pontos. \n" +
                "\n";
    }

    public String relatorioRecursosTraidores() throws IOException {
        int armaCounter = 0;
        int municaoCounter = 0;
        int aguaCounter = 0;
        int comidaCounter = 0;
        int traidorCounter = 0;
        List<Rebelde> rebeldes = rebeldesRepository.getAll();
        for (Rebelde rebelde : rebeldes) {
            // apenas Traidores
            if (rebelde.getDowngrade() >= 3) {
                armaCounter += rebelde.getInventario().getArma();
                municaoCounter += rebelde.getInventario().getMunicao();
                aguaCounter += rebelde.getInventario().getAgua();
                comidaCounter += rebelde.getInventario().getComida();
                traidorCounter++;
            }
        }
        int pontosTraidor = (armaCounter * 4) + (municaoCounter * 3) + (aguaCounter * 2) + comidaCounter;
        return ">>>>> RELATÓRIO DE TRAIDORES <<<<< \n" +
                "O número total de traidores é: " + traidorCounter + "\n" +
                "O total de armas dos Traidores é: " + armaCounter + "\n" +
                "O total de munição dos Traidores é: " + municaoCounter + "\n" +
                "O total de água dos Traidores é: " + aguaCounter + "\n" +
                "O total de comida dos Traidores é: " + comidaCounter + "\n" +
                "\n" +
                ">>>>> PONTUAÇÃO PERDIDA DOS REBELDES <<<<< \n" +
                "Os Rebeldes perderam " + pontosTraidor + " pontos para os Traidores. \n" +
                "\n";
    }

}
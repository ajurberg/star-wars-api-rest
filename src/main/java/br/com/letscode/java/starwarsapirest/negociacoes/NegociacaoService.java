package br.com.letscode.java.starwarsapirest.negociacoes;

import br.com.letscode.java.starwarsapirest.rebeldes.Rebelde;
import br.com.letscode.java.starwarsapirest.rebeldes.RebeldesRepository;
import br.com.letscode.java.starwarsapirest.rebeldes.RebeldesService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NegociacaoService {

    private final RebeldesService rebeldesService;
    private final RebeldesRepository rebeldesRepository;

    @SneakyThrows
    public String negociar(Negociacao negociacao) {
        String mensagemDeRetorno = "";
        Rebelde rebelde1 = rebeldesService.findByIdRebelde(negociacao.getIdRebelde1());
        Rebelde rebelde2 = rebeldesService.findByIdRebelde(negociacao.getIdRebelde2());
        if (rebelde1 == null || rebelde2 == null
                || rebelde1.getDowngrade() >= 3 || rebelde2.getDowngrade() >= 3 ) {
            mensagemDeRetorno = "ID não encontrado na base rebelde. Verifique se preencheu corretamente o ID ou verifique a lista de traidores";
        } else if (verificarEstoqueNegociacao(negociacao, rebelde1, rebelde2) || verificarPontosNegociacao(negociacao)) {
            mensagemDeRetorno = realizarTroca(negociacao, rebelde1, rebelde2);
        }
        return mensagemDeRetorno;
    }

    public Boolean verificarEstoqueNegociacao(Negociacao negociacao, Rebelde rebelde1, Rebelde rebelde2) {
        return negociacao.getInventarioRebelde1().getAgua() <= rebelde1.getInventario().getAgua() &&
                negociacao.getInventarioRebelde1().getArma() <= rebelde1.getInventario().getArma() &&
                negociacao.getInventarioRebelde1().getComida() <= rebelde1.getInventario().getComida() &&
                negociacao.getInventarioRebelde1().getMunicao() <= rebelde1.getInventario().getMunicao() &&
                negociacao.getInventarioRebelde2().getAgua() <= rebelde2.getInventario().getAgua() &&
                negociacao.getInventarioRebelde2().getArma() <= rebelde2.getInventario().getArma() &&
                negociacao.getInventarioRebelde2().getComida() <= rebelde2.getInventario().getComida() &&
                negociacao.getInventarioRebelde2().getMunicao() <= rebelde2.getInventario().getMunicao();
    }

    public Boolean verificarPontosNegociacao(Negociacao negociacao) {
        Integer pontosArmaRebelde1 = negociacao.getInventarioRebelde1().getArma() * 4;
        Integer pontosMunicaoRebelde1 = negociacao.getInventarioRebelde1().getMunicao() * 3;
        Integer pontosAguaRebelde1 = negociacao.getInventarioRebelde1().getAgua() * 2;
        Integer pontosComidaRebelde1 = negociacao.getInventarioRebelde1().getComida();
        int pontosTotaisRebelde1 = pontosArmaRebelde1 + pontosMunicaoRebelde1 + pontosAguaRebelde1 + pontosComidaRebelde1;

        Integer pontosArmaRebelde2 = negociacao.getInventarioRebelde2().getArma() * 4;
        Integer pontosMunicaoRebelde2 = negociacao.getInventarioRebelde2().getMunicao() * 3;
        Integer pontosAguaRebelde2 = negociacao.getInventarioRebelde2().getAgua() * 2;
        Integer pontosComidaRebelde2 = negociacao.getInventarioRebelde2().getComida();
        int pontosTotaisRebelde2 = pontosArmaRebelde2 + pontosMunicaoRebelde2 + pontosAguaRebelde2 + pontosComidaRebelde2;

        return pontosTotaisRebelde1 == pontosTotaisRebelde2;
    }

    @SneakyThrows
    public String realizarTroca(Negociacao negociacao, Rebelde rebelde1, Rebelde rebelde2) {
        if (negociacao.getInventarioRebelde1().getAgua() > 0) {
            rebelde2.getInventario().setAgua(rebelde2.getInventario().getAgua() + negociacao.getInventarioRebelde1().getAgua());
            rebelde1.getInventario().setAgua(rebelde1.getInventario().getAgua() - negociacao.getInventarioRebelde1().getAgua());
        }
        if (negociacao.getInventarioRebelde1().getComida() > 0) {
            rebelde2.getInventario().setComida(rebelde2.getInventario().getComida() + negociacao.getInventarioRebelde1().getComida());
            rebelde1.getInventario().setComida(rebelde1.getInventario().getComida() - negociacao.getInventarioRebelde1().getComida());
        }
        if (negociacao.getInventarioRebelde1().getArma() > 0) {
            rebelde2.getInventario().setArma(rebelde2.getInventario().getArma() + negociacao.getInventarioRebelde1().getArma());
            rebelde1.getInventario().setArma(rebelde1.getInventario().getArma() - negociacao.getInventarioRebelde1().getArma());
        }
        if (negociacao.getInventarioRebelde1().getMunicao() > 0) {
            rebelde2.getInventario().setMunicao(rebelde2.getInventario().getMunicao() + negociacao.getInventarioRebelde1().getMunicao());
            rebelde1.getInventario().setMunicao(rebelde1.getInventario().getMunicao() - negociacao.getInventarioRebelde1().getMunicao());
        }
        if (negociacao.getInventarioRebelde2().getAgua() > 0) {
            rebelde1.getInventario().setAgua(rebelde1.getInventario().getAgua() + negociacao.getInventarioRebelde2().getAgua());
            rebelde2.getInventario().setAgua(rebelde2.getInventario().getAgua() - negociacao.getInventarioRebelde2().getAgua());
        }
        if (negociacao.getInventarioRebelde2().getComida() > 0) {
            rebelde1.getInventario().setComida(rebelde1.getInventario().getComida() + negociacao.getInventarioRebelde2().getComida());
            rebelde2.getInventario().setComida(rebelde2.getInventario().getComida() - negociacao.getInventarioRebelde2().getComida());
        }
        if (negociacao.getInventarioRebelde2().getArma() > 0) {
            rebelde1.getInventario().setArma(rebelde1.getInventario().getArma() + negociacao.getInventarioRebelde2().getArma());
            rebelde2.getInventario().setArma(rebelde2.getInventario().getArma() - negociacao.getInventarioRebelde2().getArma());
        }
        if (negociacao.getInventarioRebelde2().getMunicao() > 0) {
            rebelde1.getInventario().setMunicao(rebelde1.getInventario().getMunicao() + negociacao.getInventarioRebelde2().getMunicao());
            rebelde2.getInventario().setMunicao(rebelde2.getInventario().getMunicao() - negociacao.getInventarioRebelde2().getMunicao());
        }
        rebeldesRepository.atualizarNoArquivo(rebelde1);
        rebeldesRepository.atualizarNoArquivo(rebelde2);
        return "Negociação efetuada com sucesso. \n\n" + rebelde1 + "\n\n" + rebelde2;
    }

}

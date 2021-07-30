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
        Rebelde negociadorA = rebeldesService.findByIdRebelde(negociacao.getIdRebelde1());
        Rebelde negociadorB = rebeldesService.findByIdRebelde(negociacao.getIdRebelde2());
        if (negociadorA == null || negociadorB == null
                || negociadorA.getDowngrade() >= 3 || negociadorB.getDowngrade() >= 3 ) {
            mensagemDeRetorno = "ID não encontrado na base rebelde. Verifique se preencheu corretamente o ID ou verifique a lista de traidores";
        } else if (verificarEstoqueNegociacao(negociacao, negociadorA, negociadorB) || verificarPontosNegociacao(negociacao)) {
            mensagemDeRetorno = realizarTroca(negociacao, negociadorA, negociadorB);
        }
        return mensagemDeRetorno;
    }

    public Boolean verificarEstoqueNegociacao(Negociacao negociacao, Rebelde negociadorA, Rebelde negociadorB) {
        return negociacao.getInventarioRebelde1().getAgua() <= negociadorA.getInventario().getAgua() &&
                negociacao.getInventarioRebelde1().getArma() <= negociadorA.getInventario().getArma() &&
                negociacao.getInventarioRebelde1().getComida() <= negociadorA.getInventario().getComida() &&
                negociacao.getInventarioRebelde1().getMunicao() <= negociadorA.getInventario().getMunicao() &&
                negociacao.getInventarioRebelde2().getAgua() <= negociadorB.getInventario().getAgua() &&
                negociacao.getInventarioRebelde2().getArma() <= negociadorB.getInventario().getArma() &&
                negociacao.getInventarioRebelde2().getComida() <= negociadorB.getInventario().getComida() &&
                negociacao.getInventarioRebelde2().getMunicao() <= negociadorB.getInventario().getMunicao();
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
    public String realizarTroca(Negociacao negociacao, Rebelde negociadorA, Rebelde negociadorB) {
        if (negociacao.getInventarioRebelde1().getAgua() > 0) {
            negociadorB.getInventario().setAgua(negociadorB.getInventario().getAgua() + negociacao.getInventarioRebelde1().getAgua());
            negociadorA.getInventario().setAgua(negociadorA.getInventario().getAgua() - negociacao.getInventarioRebelde1().getAgua());
        }
        if (negociacao.getInventarioRebelde1().getComida() > 0) {
            negociadorB.getInventario().setComida(negociadorB.getInventario().getComida() + negociacao.getInventarioRebelde1().getComida());
            negociadorA.getInventario().setComida(negociadorA.getInventario().getComida() - negociacao.getInventarioRebelde1().getComida());
        }
        if (negociacao.getInventarioRebelde1().getArma() > 0) {
            negociadorB.getInventario().setArma(negociadorB.getInventario().getArma() + negociacao.getInventarioRebelde1().getArma());
            negociadorA.getInventario().setArma(negociadorA.getInventario().getArma() - negociacao.getInventarioRebelde1().getArma());
        }
        if (negociacao.getInventarioRebelde1().getMunicao() > 0) {
            negociadorB.getInventario().setMunicao(negociadorB.getInventario().getMunicao() + negociacao.getInventarioRebelde1().getMunicao());
            negociadorA.getInventario().setMunicao(negociadorA.getInventario().getMunicao() - negociacao.getInventarioRebelde1().getMunicao());
        }
        if (negociacao.getInventarioRebelde2().getAgua() > 0) {
            negociadorA.getInventario().setAgua(negociadorA.getInventario().getAgua() + negociacao.getInventarioRebelde2().getAgua());
            negociadorB.getInventario().setAgua(negociadorB.getInventario().getAgua() - negociacao.getInventarioRebelde2().getAgua());
        }
        if (negociacao.getInventarioRebelde2().getComida() > 0) {
            negociadorA.getInventario().setComida(negociadorA.getInventario().getComida() + negociacao.getInventarioRebelde2().getComida());
            negociadorB.getInventario().setComida(negociadorB.getInventario().getComida() - negociacao.getInventarioRebelde2().getComida());
        }
        if (negociacao.getInventarioRebelde2().getArma() > 0) {
            negociadorA.getInventario().setArma(negociadorA.getInventario().getArma() + negociacao.getInventarioRebelde2().getArma());
            negociadorB.getInventario().setArma(negociadorB.getInventario().getArma() - negociacao.getInventarioRebelde2().getArma());
        }
        if (negociacao.getInventarioRebelde2().getMunicao() > 0) {
            negociadorA.getInventario().setMunicao(negociadorA.getInventario().getMunicao() + negociacao.getInventarioRebelde2().getMunicao());
            negociadorB.getInventario().setMunicao(negociadorB.getInventario().getMunicao() - negociacao.getInventarioRebelde2().getMunicao());
        }
        rebeldesRepository.atualizarNoArquivo(negociadorA);
        rebeldesRepository.atualizarNoArquivo(negociadorB);
        return "Negociação efetuada com sucesso. \n\n" + negociadorA + "\n\n" + negociadorB;
    }
}

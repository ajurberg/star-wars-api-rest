package br.com.letscode.java.starwarsapirest.rebeldes;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class RebeldesRepository {

    private Path rebeldePath;

    @PostConstruct
    public void init() {
        final String pathRebelde = ".\\src\\main\\resources\\rebeldes.csv";
        this.rebeldePath = Paths.get(pathRebelde);
    }

    public void inserirNoArquivo(Rebelde rebelde) {
        try (BufferedWriter bf = Files.newBufferedWriter(rebeldePath, StandardOpenOption.APPEND)) {
            bf.write(formatar(rebelde));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removerNoArquivo(Integer idRebelde) throws IOException {
        List<Rebelde> rebeldes = getAll();
        List<Rebelde> rebeldesResultante = new ArrayList<>();
        for (Rebelde rebelde : rebeldes) {
            if (!rebelde.getIdRebelde().equals(idRebelde)) {
                rebeldesResultante.add(rebelde);
            }
        }
        reescreverNoArquivo(rebeldesResultante);
    }

    public void atualizarNoArquivo(Rebelde rebeldeAtualizado) throws IOException {
        removerNoArquivo(rebeldeAtualizado.getIdRebelde());
        inserirNoArquivo(rebeldeAtualizado);
    }

    private void reescreverNoArquivo(List<Rebelde> rebeldes) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (Rebelde rebeldeBuilder: rebeldes) {
            builder.append(formatar(rebeldeBuilder));
        }
        write(builder.toString());
    }

    // Escreve o arquivo ou adiciona um conteudo junto ao mesmo
    private void write(String rebeldeString) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(rebeldePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            bf.write(rebeldeString);
        }
    }

    private String formatar(Rebelde rebelde) {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;\n",
                rebelde.getIdRebelde(),
                rebelde.getNome(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                rebelde.getDowngrade(),
                rebelde.getLocalizacao().getLatitude(),
                rebelde.getLocalizacao().getLongitude(),
                rebelde.getLocalizacao().getNomeGalaxia(),
                rebelde.getLocalizacao().getNomeBase(),
                rebelde.getInventario().getArma(),
                rebelde.getInventario().getMunicao(),
                rebelde.getInventario().getAgua(),
                rebelde.getInventario().getComida());
    }

    public List<Rebelde> getAll() throws IOException {
        List<Rebelde> rebeldes;
        try (BufferedReader br = Files.newBufferedReader(rebeldePath)) {
            rebeldes = br.lines().
                    filter(Objects::nonNull).
                    filter(Predicate.not(String::isEmpty)).
                    map(this::convert).collect(Collectors.toList());
        }
        return rebeldes;
    }

    private Rebelde convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        Rebelde rebelde = new Rebelde();
        rebelde.setIdRebelde(Integer.valueOf(token.nextToken()));
        rebelde.setNome(token.nextToken());
        rebelde.setIdade(Integer.valueOf(token.nextToken()));
        rebelde.setGenero(GeneroEnum.valueOf(token.nextToken()));
        rebelde.setDowngrade(Integer.valueOf(token.nextToken()));
        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude((Long.valueOf(token.nextToken())));
        localizacao.setLongitude((Long.valueOf(token.nextToken())));
        localizacao.setNomeGalaxia((token.nextToken()));
        localizacao.setNomeBase((token.nextToken()));
        rebelde.setLocalizacao(localizacao);
        Inventario inventario = new Inventario();
        inventario.setArma(Integer.valueOf(token.nextToken()));
        inventario.setMunicao(Integer.valueOf(token.nextToken()));
        inventario.setAgua(Integer.valueOf(token.nextToken()));
        inventario.setComida(Integer.valueOf(token.nextToken()));
        rebelde.setInventario(inventario);
        return rebelde;
    }
}






package br.com.letscode.java.starwarsapirest.negociacao;

import br.com.letscode.java.starwarsapirest.negociacoes.Negociacao;
import br.com.letscode.java.starwarsapirest.negociacoes.NegociacaoService;
import br.com.letscode.java.starwarsapirest.rebeldes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NegociacaoServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NegociacaoService negociacaoService;

    private Rebelde rebeldeArthur() {
        Rebelde rebelde = new Rebelde();
        rebelde.setIdRebelde(1);
        rebelde.setNome("Arthur");
        rebelde.setIdade(19);
        rebelde.setGenero(GeneroEnum.MASCULINO);
        rebelde.setDowngrade(0);
        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude(100L);
        localizacao.setLongitude(200L);
        localizacao.setNomeGalaxia("ViaLactea");
        localizacao.setNomeBase("Terra");
        rebelde.setLocalizacao(localizacao);
        Inventario inventario = new Inventario();
        inventario.setArma(0);
        inventario.setMunicao(0);
        inventario.setAgua(4);
        inventario.setComida(0);
        rebelde.setInventario(inventario);
        return rebelde;
    }

    private Rebelde rebeldeJesse() {
        Rebelde rebelde = new Rebelde();
        rebelde.setIdRebelde(2);
        rebelde.setNome("Jesse");
        rebelde.setIdade(37);
        rebelde.setGenero(GeneroEnum.MASCULINO);
        rebelde.setDowngrade(0);
        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude(300L);
        localizacao.setLongitude(250L);
        localizacao.setNomeGalaxia("ViaLactea");
        localizacao.setNomeBase("Terra");
        rebelde.setLocalizacao(localizacao);
        Inventario inventario = new Inventario();
        inventario.setArma(2);
        inventario.setMunicao(0);
        inventario.setAgua(0);
        inventario.setComida(0);
        rebelde.setInventario(inventario);
        return rebelde;
    }

    @Test
    void negociar() throws Exception {
        Mockito.when(this.negociacaoService.negociar(Mockito.any()))
                .thenReturn("efetuada com sucesso");
        var negociacao = new Negociacao();
        negociacao.setIdRebelde1(rebeldeArthur().getIdRebelde());
        negociacao.setInventarioRebelde1(rebeldeArthur().getInventario());
        negociacao.setIdRebelde2(rebeldeJesse().getIdRebelde());
        negociacao.setInventarioRebelde2(rebeldeJesse().getInventario());
        mockMvc.perform(post("/negociacao")
                .content(asJsonString(negociacao))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("efetuada com sucesso")));
    }

    @Test
    void negociarTestNull() throws Exception {
        Mockito.when(this.negociacaoService.negociar(Mockito.any()))
                .thenReturn("Verifique se preencheu corretamente o ID.");
        var negociacao = new Negociacao();
        negociacao.setIdRebelde2(rebeldeJesse().getIdRebelde());
        negociacao.setInventarioRebelde2(rebeldeJesse().getInventario());
        mockMvc.perform(post("/negociacao")
                .content(asJsonString(negociacao))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Verifique se preencheu corretamente o ID.")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.letscode.java.starwarsapirest.rebeldes;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RebeldesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RebeldesService rebeldesService;

    private Rebelde rebelde() {
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
        inventario.setArma(1);
        inventario.setMunicao(1);
        inventario.setAgua(1);
        inventario.setComida(1);
        rebelde.setInventario(inventario);
        return rebelde;
    }

    @Test
    public void createRebelde() throws Exception {
        Mockito.when(this.rebeldesService.addRebeldeService(Mockito.any()))
                .thenReturn("Arthur");
        Rebelde rebelde = rebelde();
        mockMvc.perform(post("/rebeldes")
                .content(asJsonString(rebelde))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Arthur")));
    }

    @Test
    public void AtualizarRebelde() throws Exception {
        Mockito.when(this.rebeldesService.updateLocationRebeldeService(Mockito.any()))
                .thenReturn("atualizado com sucesso!");
        var rebelde = rebelde();
        var rebeldeDto = new RebeldeDTO();
        rebeldeDto.setIdRebelde(1);
        rebeldeDto.setLocalizacao(rebelde.getLocalizacao());
        mockMvc.perform(put("/rebeldes")
        .content(asJsonString(rebeldeDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("atualizado com sucesso!")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

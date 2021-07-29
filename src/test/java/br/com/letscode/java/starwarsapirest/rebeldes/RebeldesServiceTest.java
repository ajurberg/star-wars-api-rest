package br.com.letscode.java.starwarsapirest.rebeldes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RebeldesServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarRebeldes() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rebeldes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("desi")));
    }

    @Test
    public void reportarTraidor() throws Exception {
        mockMvc.perform(post("/rebeldes/traidor").content(String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

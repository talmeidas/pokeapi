package io.github.talmeidas.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.talmeidas.dto.PokemonChallengeRequestDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PokemonChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    public void testGetWithChallengerMajorThanChallengedSuccess() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger("ivysaur")
                .challenged("bulbasaur")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("winner").value("ivysaur"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    public void testGetWithChallengerMinorThanChallengedSuccess() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger("bulbasaur")
                .challenged("ivysaur")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("winner").value("ivysaur"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    public void testGetWithChallengerEqualThanChallengedSuccess() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger("bulbasaur")
                .challenged("bulbasaur")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("winner").value("DRAW"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    public void testGetWithoutValues() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger(null)
                .challenged(null)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(5)
    public void testGetWithNotFound() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger("aaa")
                .challenged("bbb")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge/")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
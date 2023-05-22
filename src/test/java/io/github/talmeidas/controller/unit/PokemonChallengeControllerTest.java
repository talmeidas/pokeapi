package io.github.talmeidas.controller.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.talmeidas.controller.PokemonChallengeController;
import io.github.talmeidas.controller.PokemonEvolutionChainController;
import io.github.talmeidas.dto.PokemonChallengeRequestDTO;
import io.github.talmeidas.dto.PokemonChallengeResponseDTO;
import io.github.talmeidas.dto.PokemonEvolutionChainResponseDTO;
import io.github.talmeidas.service.PokemonChallengeService;
import io.github.talmeidas.service.PokemonEvolutionChainService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PokemonChallengeControllerTest {

    @Mock
    private PokemonChallengeService service;

    @InjectMocks
    private PokemonChallengeController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
//        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

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

        PokemonChallengeResponseDTO pokemonChallengeResponse = PokemonChallengeResponseDTO.builder()
                .winner("ivysaur")
                .build();

        Mockito.when(service.postPokemonChallenge(pokemonChallengeRequest)).thenReturn(pokemonChallengeResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("winner").value("ivysaur"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(service, Mockito.times(1)).postPokemonChallenge(pokemonChallengeRequest);
    }

    @Test
    @Order(2)
    public void testGetWithChallengerMinorThanChallengedSuccess() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger("bulbasaur")
                .challenged("ivysaur")
                .build();

        PokemonChallengeResponseDTO pokemonChallengeResponse = PokemonChallengeResponseDTO.builder()
                .winner("ivysaur")
                .build();

        Mockito.when(service.postPokemonChallenge(pokemonChallengeRequest)).thenReturn(pokemonChallengeResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("winner").value("ivysaur"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(service, Mockito.times(1)).postPokemonChallenge(pokemonChallengeRequest);
    }

    @Test
    @Order(3)
    public void testGetWithChallengerEqualThanChallengedSuccess() throws Exception {
        PokemonChallengeRequestDTO pokemonChallengeRequest = PokemonChallengeRequestDTO.builder()
                .challenger("bulbasaur")
                .challenged("bulbasaur")
                .build();

        PokemonChallengeResponseDTO pokemonChallengeResponse = PokemonChallengeResponseDTO.builder()
                .winner("DRAW")
                .build();

        Mockito.when(service.postPokemonChallenge(pokemonChallengeRequest)).thenReturn(pokemonChallengeResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/challenge")
                        .content(asJsonString(pokemonChallengeRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("winner").value("DRAW"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(service, Mockito.times(1)).postPokemonChallenge(pokemonChallengeRequest);
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

        Mockito.verify(service, Mockito.times(0)).postPokemonChallenge(pokemonChallengeRequest);
    }
}
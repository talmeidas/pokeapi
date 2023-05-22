package io.github.talmeidas.controller.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.talmeidas.controller.PokemonEvolutionChainController;
import io.github.talmeidas.dto.PokemonEvolutionChainResponseDTO;
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
class PokemonEvolutionChainControllerTest {

    @Mock
    private PokemonEvolutionChainService service;

    @InjectMocks
    private PokemonEvolutionChainController controller;

    private MockMvc mockMvc;

    private PokemonEvolutionChainResponseDTO defaultPokemonEvolutionChainResponse;

    @BeforeEach
    public void setup() {
//        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();

        this.defaultPokemonEvolutionChainResponse = PokemonEvolutionChainResponseDTO.builder()
                .forms(List.of("bulbasaur", "ivysaur"))
                .build();
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
    public void testGetWithSuccess() throws Exception {
        Mockito.when(service.getPokemonEvolutionChainByName("bulbasaur")).thenReturn(defaultPokemonEvolutionChainResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/evolution-chain/bulbasaur")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "  \"forms\": [\n" +
                        "    \"bulbasaur\",\n" +
                        "    \"ivysaur\"\n" +
                        "  ]\n" +
                        "}"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(service, Mockito.times(1)).getPokemonEvolutionChainByName("bulbasaur");
    }
}
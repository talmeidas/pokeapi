package io.github.talmeidas.controller.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.talmeidas.controller.PokemonController;
import io.github.talmeidas.dto.NamedAPIResourceDTO;
import io.github.talmeidas.dto.PokemonAPIStatDTO;
import io.github.talmeidas.dto.PokemonAPITypeDTO;
import io.github.talmeidas.dto.PokemonResponseDTO;
import io.github.talmeidas.service.PokemonService;
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
class PokemonControllerTest {

    @Mock
    private PokemonService service;

    @InjectMocks
    private PokemonController controller;

    private MockMvc mockMvc;

    private PokemonResponseDTO defaultPokemonResponse;

    @BeforeEach
    public void setup() {
//        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();

        this.defaultPokemonResponse = PokemonResponseDTO.builder()
                .name("bulbasaur")
                .height(7)
                .weight(69)
                .location_area_encounters("https://pokeapi.co/api/v2/pokemon/1/encounters")
                .stats(List.of(
                        PokemonAPIStatDTO.builder()
                                .stat(NamedAPIResourceDTO.builder().name("hp").url("https://pokeapi.co/api/v2/stat/1/").build())
                                .effort(0)
                                .base_stat(45)
                                .build(),
                        PokemonAPIStatDTO.builder()
                                .stat(NamedAPIResourceDTO.builder().name("attack").url("https://pokeapi.co/api/v2/stat/2/").build())
                                .effort(0)
                                .base_stat(49)
                                .build(),
                        PokemonAPIStatDTO.builder()
                                .stat(NamedAPIResourceDTO.builder().name("defense").url("https://pokeapi.co/api/v2/stat/3/").build())
                                .effort(0)
                                .base_stat(49)
                                .build(),
                        PokemonAPIStatDTO.builder()
                                .stat(NamedAPIResourceDTO.builder().name("special-attack").url("https://pokeapi.co/api/v2/stat/4/").build())
                                .effort(1)
                                .base_stat(65)
                                .build(),
                        PokemonAPIStatDTO.builder()
                                .stat(NamedAPIResourceDTO.builder().name("special-defense").url("https://pokeapi.co/api/v2/stat/5/").build())
                                .effort(0)
                                .base_stat(65)
                                .build(),
                        PokemonAPIStatDTO.builder()
                                .stat(NamedAPIResourceDTO.builder().name("speed").url("https://pokeapi.co/api/v2/stat/6/").build())
                                .effort(0)
                                .base_stat(45)
                                .build()
                ))
                .types(List.of(
                        PokemonAPITypeDTO.builder()
                                .slot(1)
                                .type(NamedAPIResourceDTO.builder().name("grass").url("https://pokeapi.co/api/v2/type/12/").build())
                                .build(),
                        PokemonAPITypeDTO.builder()
                                .slot(2)
                                .type(NamedAPIResourceDTO.builder().name("poison").url("https://pokeapi.co/api/v2/type/4/").build())
                                .build()
                ))
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
        Mockito.when(service.getPokemonByName("bulbasaur")).thenReturn(defaultPokemonResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/pokemon/bulbasaur")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("bulbasaur"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(service, Mockito.times(1)).getPokemonByName("bulbasaur");
    }
}
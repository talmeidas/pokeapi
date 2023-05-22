package io.github.talmeidas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class PokemonResponseDTO {
    private String name;
    private Integer height;
    private Integer weight;
    private String location_area_encounters;
    private List<PokemonAPIStatDTO> stats;
    private List<PokemonAPITypeDTO> types;
}

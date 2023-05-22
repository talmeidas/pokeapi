package io.github.talmeidas.dto;

import lombok.*;

@Getter
@Builder
public class PokemonAPISpeciesResponseDTO {
    private Integer id;
    private String name;
    private NamedAPIResourceDTO evolves_from_species;
    private APIResourceDTO evolution_chain;
}

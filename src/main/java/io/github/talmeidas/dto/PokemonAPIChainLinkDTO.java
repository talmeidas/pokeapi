package io.github.talmeidas.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PokemonAPIChainLinkDTO {
    private NamedAPIResourceDTO species;
    private List<PokemonAPIChainLinkDTO> evolves_to;
}

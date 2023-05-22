package io.github.talmeidas.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PokemonAPITypeDTO {
    private Integer slot;
    private NamedAPIResourceDTO type;
}

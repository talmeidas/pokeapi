package io.github.talmeidas.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PokemonAPIStatDTO {
    private NamedAPIResourceDTO stat;
    private Integer effort;
    private Integer base_stat;
}

package io.github.talmeidas.dto;

import lombok.Getter;

@Getter
public class PokemonAPIStatDTO {
    private NamedAPIResourceDTO stat;
    private Integer effort;
    private Integer base_stat;
}

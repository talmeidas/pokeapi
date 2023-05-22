package io.github.talmeidas.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PokemonChallengeResponseDTO {
    private String winner;
}

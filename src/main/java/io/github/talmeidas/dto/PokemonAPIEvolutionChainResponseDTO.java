package io.github.talmeidas.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonAPIEvolutionChainResponseDTO {
    private PokemonAPIChainLinkDTO chain;
}

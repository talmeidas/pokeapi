package io.github.talmeidas.mapper;

import io.github.talmeidas.dto.PokemonChallengeResponseDTO;
import io.github.talmeidas.dto.PokemonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PokemonChallengeMapper {
    public PokemonChallengeResponseDTO toResponse(String entity) {
        Objects.requireNonNull(entity, "entity must not be null");

        return PokemonChallengeResponseDTO.builder()
                .winner(entity)
                .build();
    }
}

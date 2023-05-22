package io.github.talmeidas.mapper;

import io.github.talmeidas.dto.PokemonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PokemonMapper {
    public PokemonResponseDTO toResponse(PokemonResponseDTO entity) {
        Objects.requireNonNull(entity, "entity must not be null");

        return PokemonResponseDTO.builder()
                .name(entity.getName())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .location_area_encounters(entity.getLocation_area_encounters())
                .stats(entity.getStats())
                .types(entity.getTypes())
                .build();
    }
}

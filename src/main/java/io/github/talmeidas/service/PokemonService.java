package io.github.talmeidas.service;

import io.github.talmeidas.client.PokemonClient;
import io.github.talmeidas.dto.PokemonResponseDTO;
import io.github.talmeidas.mapper.PokemonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonMapper mapper;
    private final PokemonClient client;

    public PokemonResponseDTO getPokemonByName(String name) {
        log.info("Getting pokemon by name - {}", name);

        var entityPokemon = client.getPokemonByName(name);

        return mapper.toResponse(entityPokemon);
    }
}

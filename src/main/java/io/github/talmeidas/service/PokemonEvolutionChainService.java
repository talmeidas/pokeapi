package io.github.talmeidas.service;

import io.github.talmeidas.client.PokemonClient;
import io.github.talmeidas.dto.PokemonEvolutionChainResponseDTO;
import io.github.talmeidas.mapper.PokemonEvolutionChainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class PokemonEvolutionChainService {
    private final PokemonEvolutionChainMapper mapper;
    private final PokemonClient client;

//    @Value("${client.pokemon.url}")
    private static final String URL_EVOLUTION_CHAIN = "https://pokeapi.co/api/v2/evolution-chain/";

    public PokemonEvolutionChainResponseDTO getPokemonEvolutionChainByName(String name) {
        log.info("Getting pokemon evolution by name - {}", name);

        var entityPokemonSpecie = client.getPokemonSpecieByName(name);

        String urlEvolutionChain = entityPokemonSpecie.getEvolution_chain().getUrl();

        Integer idEvolutionChain = Integer.valueOf(
                urlEvolutionChain
                        .replace(URL_EVOLUTION_CHAIN, "")
                        .replace("/", ""));

        var entityPokemonEvolutionChain = client.getPokemonEvolutionChainByName(idEvolutionChain);

        return mapper.toResponse(entityPokemonEvolutionChain);
    }
}

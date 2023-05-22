package io.github.talmeidas.client;

import io.github.talmeidas.dto.PokemonAPIEvolutionChainResponseDTO;
import io.github.talmeidas.dto.PokemonResponseDTO;
import io.github.talmeidas.dto.PokemonAPISpeciesResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "pokeapi", url = "${client.pokemon.url}")
public interface PokemonClient {
    @GetMapping("/v2/pokemon/{name}")
    PokemonResponseDTO getPokemonByName(@PathVariable("name") final String name);

    @GetMapping("/v2/pokemon-species/{name}")
    PokemonAPISpeciesResponseDTO getPokemonSpecieByName(@PathVariable("name") final String name);

    @GetMapping("/v2/evolution-chain/{id}")
    PokemonAPIEvolutionChainResponseDTO getPokemonEvolutionChainByName(@PathVariable("id") final Integer id);
}

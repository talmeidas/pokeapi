package io.github.talmeidas.mapper;

import io.github.talmeidas.dto.PokemonAPIChainLinkDTO;
import io.github.talmeidas.dto.PokemonAPIEvolutionChainResponseDTO;
import io.github.talmeidas.dto.PokemonEvolutionChainResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PokemonEvolutionChainMapper {
    public PokemonEvolutionChainResponseDTO toResponse(PokemonAPIEvolutionChainResponseDTO entity) {
        Objects.requireNonNull(entity, "entity must not be null");

        List<String> evolutionChainList = new ArrayList<>();

        mapEvolutionChain(evolutionChainList, entity.getChain());

        return PokemonEvolutionChainResponseDTO.builder()
                .forms(evolutionChainList)
                .build();
    }

    private void mapEvolutionChain(List<String> evolutionChainList, PokemonAPIChainLinkDTO evolutionChain) {
        if (!evolutionChain.getEvolves_to().isEmpty()) {
            evolutionChainList.add(evolutionChain.getSpecies().getName());
            mapEvolutionChain(evolutionChainList, evolutionChain.getEvolves_to().get(0));
        }
    }
}

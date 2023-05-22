package io.github.talmeidas.service;

import io.github.talmeidas.client.PokemonClient;
import io.github.talmeidas.dto.PokemonAPIStatDTO;
import io.github.talmeidas.dto.PokemonChallengeRequestDTO;
import io.github.talmeidas.dto.PokemonChallengeResponseDTO;
import io.github.talmeidas.mapper.PokemonChallengeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonChallengeService {
    private final PokemonChallengeMapper mapper;
    private final PokemonClient client;
    private static final String CHALLENGE_DRAW = "DRAW";

    public PokemonChallengeResponseDTO postPokemonChallenge(PokemonChallengeRequestDTO pokemonChallengeRequest) {
        log.info("Challenge pokemon - {}", pokemonChallengeRequest);

        var entityPokemonChallenger = client.getPokemonByName(pokemonChallengeRequest.getChallenger());
        var entityPokemonChallenged = client.getPokemonByName(pokemonChallengeRequest.getChallenged());

        int totalChallenger = entityPokemonChallenger.getStats().stream().mapToInt(PokemonAPIStatDTO::getBase_stat).sum();
        int totalChallenged = entityPokemonChallenged.getStats().stream().mapToInt(PokemonAPIStatDTO::getBase_stat).sum();

        if (totalChallenger > totalChallenged) {
            return mapper.toResponse(entityPokemonChallenger.getName());
        } else if (totalChallenger < totalChallenged) {
            return mapper.toResponse(entityPokemonChallenged.getName());
        }

        return mapper.toResponse(CHALLENGE_DRAW);
    }
}

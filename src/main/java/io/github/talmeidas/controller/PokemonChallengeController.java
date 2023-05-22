package io.github.talmeidas.controller;

import io.github.talmeidas.controller.metric.LogExecutionTime;
import io.github.talmeidas.dto.PokemonChallengeRequestDTO;
import io.github.talmeidas.dto.PokemonChallengeResponseDTO;
import io.github.talmeidas.service.PokemonChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/challenge", produces = "application/json")
@Tag(name = "Pokemon Challenge Controller")
@Slf4j
public class PokemonChallengeController {
    private final PokemonChallengeService service;

    @LogExecutionTime
    @Operation(summary = "Post Pokemon Challenge")
    @PostMapping
    public ResponseEntity<PokemonChallengeResponseDTO> postPokemonChallenge(@Valid @RequestBody PokemonChallengeRequestDTO pokemonChallengeRequest) {
        final PokemonChallengeResponseDTO response = service.postPokemonChallenge(pokemonChallengeRequest);
        return ResponseEntity.ok(response);
    }
}

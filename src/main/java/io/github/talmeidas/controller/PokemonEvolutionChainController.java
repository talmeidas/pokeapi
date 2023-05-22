package io.github.talmeidas.controller;

import io.github.talmeidas.controller.metric.LogExecutionTime;
import io.github.talmeidas.dto.PokemonEvolutionChainResponseDTO;
import io.github.talmeidas.service.PokemonEvolutionChainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/evolution-chain", produces = "application/json")
@Tag(name = "Pokemon Evolution Controller")
@Slf4j
public class PokemonEvolutionChainController {
    private final PokemonEvolutionChainService service;

    @LogExecutionTime
    @Operation(summary = "Get Pokemon Evolution Chain")
    @GetMapping("/{name}")
    public ResponseEntity<PokemonEvolutionChainResponseDTO> getPokemonEvolutionChainByName(@PathVariable("name") final String name) {
        final PokemonEvolutionChainResponseDTO response = service.getPokemonEvolutionChainByName(name);
        return ResponseEntity.ok(response);
    }
}

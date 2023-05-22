package io.github.talmeidas.controller;

import io.github.talmeidas.controller.metric.LogExecutionTime;
import io.github.talmeidas.dto.PokemonResponseDTO;
import io.github.talmeidas.service.PokemonService;
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

import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pokemon")
@Tag(name = "Pokemon Controller")
@Slf4j
public class PokemonController {
    private final PokemonService service;

    @LogExecutionTime
    @Operation(summary = "Get Pokemon")
    @GetMapping("/{name}")
    public ResponseEntity<PokemonResponseDTO> getPokemonByName(@PathVariable("name") final String name) {
        final PokemonResponseDTO response = service.getPokemonByName(name);
        return ResponseEntity.ok(response);
    }
}

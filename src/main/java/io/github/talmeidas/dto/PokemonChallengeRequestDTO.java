package io.github.talmeidas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class PokemonChallengeRequestDTO {
    @NotNull
    @Size(min = 1, message = "Minimum length 3 characters")
    private String challenger;

    @NotNull
    @Size(min = 1, message = "Minimum length 3 characters")
    private String challenged;
}

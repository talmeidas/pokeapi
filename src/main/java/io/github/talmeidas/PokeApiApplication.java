package io.github.talmeidas;

import io.github.talmeidas.dto.PokeApiRequestDTO;
import io.github.talmeidas.enumerable.Category;
import io.github.talmeidas.enumerable.Status;
import io.github.talmeidas.service.PokeApiService;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
public class PokeApiApplication implements CommandLineRunner {
    private final PokeApiService PokeApiService;

    public static void main(String[] args) {
        SpringApplication.run(PokeApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Initial Load of driver license
        var PokeApi1 = PokeApiRequestDTO.builder()
                .name("Nome 1")
                .cpf("00000000191")
                .cnhNumber("07021871297")
                .birthDate(Date.from(Instant.parse("1980-01-01T00:00:00.000Z")))
                .expirationDate(Date.from(Instant.parse("2030-01-01T00:00:00.000Z")))
                .issueDate(Date.from(Instant.parse("2000-01-01T00:00:00.000Z")))
                .local("São Paulo, SP")
                .category(Category.C)
                .status(Status.REGULAR)
                .build();

        var PokeApi2 = PokeApiRequestDTO.builder()
                .name("Nome 2")
                .cpf("27626137019")
                .cnhNumber("69457688418")
                .birthDate(Date.from(Instant.parse("1990-01-01T00:00:00.000Z")))
                .expirationDate(Date.from(Instant.parse("2030-01-01T00:00:00.000Z")))
                .issueDate(Date.from(Instant.parse("2010-01-01T00:00:00.000Z")))
                .local("São Paulo, SP")
                .category(Category.B)
                .status(Status.IRREGULAR)
                .build();

        var PokeApi3 = PokeApiRequestDTO.builder()
                .name("Nome 3")
                .cpf("08565793001")
                .cnhNumber("21418336906")
                .birthDate(Date.from(Instant.parse("2000-01-01T00:00:00.000Z")))
                .expirationDate(Date.from(Instant.parse("2030-01-01T00:00:00.000Z")))
                .issueDate(Date.from(Instant.parse("2020-01-01T00:00:00.000Z")))
                .local("São Paulo, SP")
                .category(Category.A)
                .status(Status.REGULAR)
                .build();

        PokeApiService.savePokeApi(PokeApi1);
        PokeApiService.savePokeApi(PokeApi2);
        PokeApiService.savePokeApi(PokeApi3);
    }
}

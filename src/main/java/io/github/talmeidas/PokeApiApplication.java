package io.github.talmeidas;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PokeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokeApiApplication.class, args);
    }
}

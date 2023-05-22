package io.github.talmeidas.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NamedAPIResourceDTO {
    private String name;
    private String url;
}

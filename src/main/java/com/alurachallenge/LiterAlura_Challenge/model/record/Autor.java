package com.alurachallenge.LiterAlura_Challenge.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer cumpleaños,
        @JsonAlias("death_year") Integer fechaFallecimiento
) {
}

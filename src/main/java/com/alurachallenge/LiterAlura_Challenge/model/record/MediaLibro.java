package com.alurachallenge.LiterAlura_Challenge.model.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.print.attribute.standard.Media;


@JsonIgnoreProperties(ignoreUnknown = true)
public record MediaLibro(
        @JsonAlias("image/jpeg") String imagen
){
    public MediaLibro(Media imagen) {
        this(imagen == null ? "Sin imagen" : imagen.toString());
    }
}

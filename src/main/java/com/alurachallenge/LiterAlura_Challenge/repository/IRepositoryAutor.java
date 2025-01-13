package com.alurachallenge.LiterAlura_Challenge.repository;

import com.alurachallenge.LiterAlura_Challenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRepositoryAutor extends JpaRepository<Autor, Long> {
    List<Autor> findAll();
    List<Autor> findByCumpleañosBeforeOrFechaFallecimientoAfter(int añoMin, int añoMax);
    Optional<Autor> findFirstByNombreContainsIgnoreCase(String escritor);
}

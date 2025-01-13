package com.alurachallenge.LiterAlura_Challenge.repository;

import com.alurachallenge.LiterAlura_Challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepositoryLibro extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);
    Libro findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdioma(String idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.cantidadDescargas DESC LIMIT 10")
    List<Libro> findTop10ByTituloByCantidadDescargas();
}

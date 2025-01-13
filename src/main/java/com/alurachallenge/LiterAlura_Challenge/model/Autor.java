package com.alurachallenge.LiterAlura_Challenge.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer cumpleaños;

    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    public Autor() {}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCumpleaños() {
        return cumpleaños;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor(com.alurachallenge.LiterAlura_Challenge.model.record.Autor autor) {
        this.nombre = autor.nombre();
        this.cumpleaños = autor.cumpleaños();
        this.fechaFallecimiento = autor.fechaFallecimiento();
    }


    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                        ", cumpleaños=" + cumpleaños +
                        ", fechaFallecimiento=" + fechaFallecimiento;
    }
}

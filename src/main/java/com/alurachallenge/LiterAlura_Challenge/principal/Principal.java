package com.alurachallenge.LiterAlura_Challenge.principal;

import com.alurachallenge.LiterAlura_Challenge.config.ConsumoApi;
import com.alurachallenge.LiterAlura_Challenge.config.ConvertirDatos;
import com.alurachallenge.LiterAlura_Challenge.model.Autor;
import com.alurachallenge.LiterAlura_Challenge.model.Libro;
import com.alurachallenge.LiterAlura_Challenge.model.LibrosRespuesta;
import com.alurachallenge.LiterAlura_Challenge.record.DatosLibro;
import com.alurachallenge.LiterAlura_Challenge.repository.IRepositoryAutor;
import com.alurachallenge.LiterAlura_Challenge.repository.IRepositoryLibro;

import org.antlr.v4.runtime.InputMismatchException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private static String API_BASE = "https://gutendex.com/books/?search=";
    private List<Libro> datosLibro = new ArrayList<>();
    private IRepositoryLibro repositoryLibro;
    private IRepositoryAutor repositoryAutor;

    public Principal(IRepositoryLibro repositoryLibro, IRepositoryAutor repositoryAutor) {
        this.repositoryLibro = repositoryLibro;
        this.repositoryAutor = repositoryAutor;
    }

    public void literalura() {
        var opcion = -1;

        while (opcion != 0) {
            var menu = """
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    |******      Bienvenido a LiterAlura       ******|
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    \s
                    1 - Agregar Libro por Nombre
                    2 - Libros buscados
                    3 - Buscar libro por Nombre
                    4 - Buscar todos los Autores de libros buscados
                    5 - Buscar Autores por año
                    6 - Buscar Libros por Idioma
                    7 - Top 10 Libros mas Descargados
                    8 - Buscar Autor por Nombre
                    \s
                    \s
                    0 - Salir
                    \s
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    |******        Ingresar una opción         ******|
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    """;

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("""
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    |******     Ingresar un número válido      ******|
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    """);
                scanner.nextLine();
                continue;
            }

            switch (opcion){
                case 1:
                    buscarLibroEnLaWeb();
                    break;
                case 2:
                    librosBuscados();
                    break;
                case 3:
                    buscarLibroPorNombre();
                    break;
                case 4:
                    BuscarAutores();
                    break;
                case 5:
                    buscarAutoresPorAño();
                    break;
                case 6:
                    buscarLibrosPorIdioma();
                    break;
                case 7:
                    top10LibrosMasDescargados();
                    break;
                case 8:
                    buscarAutorPorNombre();
                    break;
                case 0:
                    opcion = 0;
                    System.out.println("""
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    |******        Programa finalizado!        ******|
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    """);
                    break;
                default:
                    System.out.println("""
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    |******      La opción es incorrecta.      ******|
                    ||||||||||||||||||||||||||||||||||||||||||||||||||
                    """);
                    System.out.println("Intente nuevamente");
                    literalura();
                    break;
            }
        }
    }


    private Libro getDatosLibro(){
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = scanner.nextLine().toLowerCase();
        var json = consumoApi.obtenerDatos(API_BASE + nombreLibro.replace(" ", "%20"));

        LibrosRespuesta datos = convertirDatos.convertirDatosJson(json, LibrosRespuesta.class);

        if (datos != null && datos.getResultadoLibros() != null && !datos.getResultadoLibros().isEmpty()) {
            DatosLibro primerLibro = datos.getResultadoLibros().get(0);
            return new Libro(primerLibro);
        } else {
            System.out.println("No se encontraron resultados.");
            return null;
        }
    }

    private void buscarLibroEnLaWeb() {
        Libro libro = getDatosLibro();

        if (libro == null){
            System.out.println("Libro no encontrado. el valor es null");
            return;
        }
        try{
            boolean libroExists = repositoryLibro.existsByTitulo(libro.getTitulo());
            if (libroExists){
                System.out.println("El libro ya existe en la base de datos!");
            }else {
                repositoryLibro.save(libro);
                System.out.println(libro.toString());
            }
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println("No se puede persisitir el libro buscado!");
        }
    }

    @Transactional(readOnly = true)
    private void librosBuscados(){
        List<Libro> libros = repositoryLibro.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("Libros encontrados en la base de datos:");
            for (Libro libro : libros) {
                System.out.println(libro.toString());
            }
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Ingrese Titulo libro que quiere buscar: ");
        var titulo = scanner.nextLine();
        Libro libroBuscado = repositoryLibro.findByTituloContainsIgnoreCase(titulo);
        if (libroBuscado != null) {
            System.out.println("El libro buscado fue: " + libroBuscado);
        } else {
            System.out.println("El libro con el titulo '" + titulo + "' no se encontró.");
        }
    }

    private  void BuscarAutores(){
        List<Autor> autores = repositoryAutor.findAll();

        if (autores.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos. \n");
        } else {
            System.out.println("Libros encontrados en la base de datos: \n");
            Set<String> autoresUnicos = new HashSet<>();
            for (Autor autor : autores) {
                if (autoresUnicos.add(autor.getNombre())){
                    System.out.println(autor.getNombre()+'\n');
                }
            }
        }
    }

    private void buscarAutoresPorAño() {
        System.out.print("Indica el año para consultar qué autores estaban vivos: ");
        int añoBuscado = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresVivos = repositoryAutor.findByCumpleañosBeforeOrFechaFallecimientoAfter(añoBuscado, añoBuscado);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + añoBuscado + ".");
            return;
        }

        System.out.println("Los autores vivos en el año " + añoBuscado + " son:");
        autoresVivos.stream()
                .filter(autor -> autor.getCumpleaños() != null && autor.getFechaFallecimiento() != null)
                .filter(autor -> autor.getCumpleaños() <= añoBuscado && autor.getFechaFallecimiento() >= añoBuscado)
                .map(Autor::getNombre)
                .distinct()
                .forEach(nombre -> System.out.println("Autor: " + nombre));
    }

    private void  buscarLibrosPorIdioma(){
        System.out.println("Ingrese Idioma en el que quiere buscar: \n");
        System.out.println("|***********************************|");
        System.out.println("|  Opción - es : Libros en español. |");
        System.out.println("|  Opción - en : Libros en ingles.  |");
        System.out.println("|***********************************|\n");

        var idioma = scanner.nextLine();
        List<Libro> librosPorIdioma = repositoryLibro.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("Libros segun idioma encontrados en la base de datos:");
            for (Libro libro : librosPorIdioma) {
                System.out.println(libro.toString());
            }
        }

    }

    private void top10LibrosMasDescargados() {
        List<Libro> top10Libros = repositoryLibro.findTop10ByTituloByCantidadDescargas();

        if (top10Libros.isEmpty()) {
            System.out.println("No se encontraron libros descargados.");
            return;
        }

        AtomicInteger index = new AtomicInteger(1);
        top10Libros.forEach(libro -> System.out.printf(
                "Libro %d: %s Autor: %s Descargas: %d%n",
                index.getAndIncrement(),
                libro.getTitulo(),
                libro.getAutores().getNombre(),
                libro.getCantidadDescargas()
        ));
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingrese el nombre del escritor que quiere buscar: ");
        String nombreEscritor = scanner.nextLine();

        repositoryAutor.findFirstByNombreContainsIgnoreCase(nombreEscritor)
                .ifPresentOrElse(
                        autor -> System.out.println("\nEl escritor encontrado es: " + autor.getNombre()),
                        () -> System.out.println("\nNo se encontró un escritor con el nombre '" + nombreEscritor + "'.")
                );
    }
}
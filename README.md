# LiterAlura - Catálogo de Libros

LiterAlura es una aplicación desarrollada en Java utilizando el framework Spring Boot. Su objetivo es proporcionar un catálogo de libros interactivo a través de una interfaz de consola que permite a los usuarios explorar y gestionar información sobre libros. La aplicación ofrece una amplia variedad de opciones de interacción textual.

## Funcionalidades Principales

1. **Agregar libro por nombre**: Permite añadir un libro al catálogo ingresando su nombre.
2. **Libros buscados**: Muestra una lista de los libros más buscados por los usuarios.
3. **Buscar libro por nombre**: Realiza búsquedas específicas de libros mediante el nombre.
4. **Buscar todos los autores de libros buscados**: Lista todos los autores de los libros que han sido buscados.
5. **Buscar autores por año**: Encuentra autores según el año de publicación de sus libros.
6. **Buscar libros por idioma**: Filtra libros disponibles según su idioma.
7. **Top 10 libros más descargados**: Presenta un ranking de los 10 libros más descargados del catálogo.
8. **Buscar autor por nombre**: Realiza una búsqueda específica de autores utilizando su nombre.
0. **Salir**: Finaliza la ejecución de la aplicación.

## Desarrollo

### Pasos realizados para crear LiterAlura:

1. **Configuración del Ambiente Java**
   - Instalación de JDK y configuración de variables de entorno.
   - Configuración de herramientas como Maven para la gestión de dependencias.

2. **Creación del Proyecto**
   - Configuración inicial del proyecto Spring Boot utilizando Spring Initializr.
   - Organización de la estructura del proyecto en paquetes.

3. **Consumo de la API**
   - Implementación de clientes para consumir datos de una API pública o interna relacionada con libros.
   - Gestión de solicitudes HTTP utilizando RestTemplate o WebClient.

4. **Análisis de la Respuesta JSON**
   - Deserialización de datos JSON obtenidos de la API.
   - Mapeo de datos a objetos Java (POJOs) utilizando bibliotecas como Jackson.

5. **Inserción y consulta en la base de datos**
   - Configuración de la base de datos utilizando Spring Data JPA.
   - Creación de entidades y repositorios para interactuar con la base de datos.

6. **Exhibición de resultados a los usuarios**
   - Diseño de un menú interactivo para la consola.
   - Presentación de datos al usuario en un formato legible.

## Requisitos del Sistema

- Java 17 o superior
- Spring Boot 3.x
- Maven 3.8+ o Gradle 7+
- Base de datos (H2, MySQL, PostgreSQL, etc.)

## Instalación y Uso

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/LiterAlura.git
   cd LiterAlura
   ```

2. **Configurar la base de datos**:
   - Modificar el archivo `application.properties` con las credenciales y URL de tu base de datos.

3. **Construir el proyecto**:
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**:
   ```bash
   java -jar target/literalura-0.0.1-SNAPSHOT.jar
   ```

5. **Interacción con la aplicación**:
   - Sigue las instrucciones mostradas en la consola para interactuar con el catálogo.

## Contribuciones

Si deseas contribuir a LiterAlura, por favor sigue estos pasos:

1. Haz un fork del repositorio.
   
2. Crea una rama para tu feature o fix:
   ```bash
   git checkout -b nombre-de-la-rama
   ```
3. Realiza tus cambios y haz commit:
   ```bash
   git commit -m "Descripción de los cambios"
   ```
4. Haz push de tus cambios:
   ```bash
   git push origin nombre-de-la-rama
   ```
5. Abre un Pull Request en GitHub.

---

Desarrollado por **Paulo Arce**.

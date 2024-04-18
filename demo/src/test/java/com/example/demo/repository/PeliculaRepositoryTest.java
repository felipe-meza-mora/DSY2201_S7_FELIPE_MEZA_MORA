package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull; 
import java.util.Calendar;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Pelicula;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest(){
        // Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titanic");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1997);
        java.sql.Date yearDate = new java.sql.Date(calendar.getTimeInMillis());

        pelicula.setYear(yearDate);
        pelicula.setDirector("James Cameron");
        pelicula.setGenero("Romance");
        pelicula.setSinopsis("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.");

        // Act
        Pelicula resultado = peliculaRepository.save(pelicula);

        // Assert
        assertNotNull(resultado.getId());
        assertEquals("Titanic", resultado.getTitulo());
        assertEquals(yearDate, resultado.getYear());
        assertEquals("James Cameron", resultado.getDirector());
        assertEquals("Romance", resultado.getGenero());
        assertEquals("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.", resultado.getSinopsis());
    }

    @Test
    public void modificarPeliculaTest() {
        // Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Avatar");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2009);
        java.sql.Date yearDate = new java.sql.Date(calendar.getTimeInMillis());

        pelicula.setYear(yearDate);
        pelicula.setDirector("James Cameron");
        pelicula.setGenero("Ciencia Ficción");
        pelicula.setSinopsis("En el año 2154, Jake Sully, un ex-marine condenado a vivir en una silla de ruedas, sigue siendo un auténtico guerrero.");

        peliculaRepository.save(pelicula);

        // Act
        pelicula.setGenero("Fantasía");
        pelicula.setSinopsis("Un ex-marine es reclutado para viajar a otro planeta...");

        Pelicula peliculaModificada = peliculaRepository.save(pelicula);

        // Assert
        assertNotNull(peliculaModificada.getId());
        assertEquals("Avatar", peliculaModificada.getTitulo());
        assertEquals(yearDate, peliculaModificada.getYear());
        assertEquals("James Cameron", peliculaModificada.getDirector());
        assertEquals("Fantasía", peliculaModificada.getGenero());
        assertEquals("Un ex-marine es reclutado para viajar a otro planeta...", peliculaModificada.getSinopsis());
    }

    @Test
    public void eliminarPeliculaTest() {
        // Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titanic");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1997);
        java.sql.Date yearDate = new java.sql.Date(calendar.getTimeInMillis());

        pelicula.setYear(yearDate);
        pelicula.setDirector("James Cameron");
        pelicula.setGenero("Romance");
        pelicula.setSinopsis("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.");

        peliculaRepository.save(pelicula);

        // Act
        peliculaRepository.delete(pelicula);

        // Assert
        Optional<Pelicula> resultado = peliculaRepository.findById(pelicula.getId());
        assertFalse(resultado.isPresent(), "La película no debería estar presente en el repositorio después de la eliminación");
    }

}
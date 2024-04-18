package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServicelmpl peliculaService;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void guardarPeliculaTest(){
        //Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titanic");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1997);
        java.sql.Date yearDate = new java.sql.Date(calendar.getTimeInMillis());

        pelicula.setYear(yearDate);
        pelicula.setDirector("James Cameron");
        pelicula.setGenero("Romance");
        pelicula.setSinopsis("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        //Act
        Pelicula resultado = peliculaService.createPelicula(pelicula);

        //Assert
        assertEquals("Titanic", resultado.getTitulo());
        assertEquals(yearDate, resultado.getYear());
        assertEquals("James Cameron", resultado.getDirector()); 
        assertEquals("Romance", resultado.getGenero());
        assertEquals("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.", resultado.getSinopsis());

    }

    @Test
    public void listarPeliculaTest() {
        // Arrange
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1);
        pelicula1.setTitulo("Titanic");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 1997);
        java.sql.Date yearDate1 = new java.sql.Date(calendar1.getTimeInMillis());

        pelicula1.setYear(yearDate1);
        pelicula1.setDirector("James Cameron");
        pelicula1.setGenero("Romance");
        pelicula1.setSinopsis("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2);
        pelicula2.setTitulo("Avatar");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2009);
        java.sql.Date yearDate2 = new java.sql.Date(calendar2.getTimeInMillis());

        pelicula2.setYear(yearDate2);
        pelicula2.setDirector("James Cameron");
        pelicula2.setGenero("Ciencia Ficción");
        pelicula2.setSinopsis("En el año 2154, Jake Sully, un ex-marine condenado a vivir en una silla de ruedas, sigue siendo un auténtico guerrero.");

        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(pelicula1);
        peliculas.add(pelicula2);

        // Configurar el comportamiento del mock de peliculaRepository para que devuelva todas las películas
        when(peliculaRepositoryMock.findAll()).thenReturn(peliculas);

        // Act
        List<Pelicula> peliculasListadas = peliculaService.getAllPeliculas();

        // Assert
        assertNotNull(peliculasListadas, "La lista de películas no debería ser nula");
        assertEquals(2, peliculasListadas.size(), "Se esperan dos películas en la lista");

        // Verificar la primera película
        Pelicula primeraPelicula = peliculasListadas.get(0);
        assertEquals(pelicula1.getId(), primeraPelicula.getId());
        assertEquals("Titanic", primeraPelicula.getTitulo());
        assertEquals(yearDate1, primeraPelicula.getYear());
        assertEquals("James Cameron", primeraPelicula.getDirector());
        assertEquals("Romance", primeraPelicula.getGenero());
        assertEquals("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.", primeraPelicula.getSinopsis());

        // Verificar la segunda película
        Pelicula segundaPelicula = peliculasListadas.get(1);
        assertEquals(pelicula2.getId(), segundaPelicula.getId());
        assertEquals("Avatar", segundaPelicula.getTitulo());
        assertEquals(yearDate2, segundaPelicula.getYear());
        assertEquals("James Cameron", segundaPelicula.getDirector());
        assertEquals("Ciencia Ficción", segundaPelicula.getGenero());
        assertEquals("En el año 2154, Jake Sully, un ex-marine condenado a vivir en una silla de ruedas, sigue siendo un auténtico guerrero.", segundaPelicula.getSinopsis());
    }
    

    
}

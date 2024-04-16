package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Calendar;

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
        Date yearDate = (Date) calendar.getTime();

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
    
}

package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull; 
import java.sql.Date;
import java.util.Calendar;

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

        //Act
        Pelicula resultado = peliculaRepository.save(pelicula);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("Titanic", resultado.getTitulo());
        assertEquals(yearDate, resultado.getYear());
        assertEquals("James Cameron", resultado.getDirector());
        assertEquals("Romance", resultado.getGenero());
        assertEquals("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.", resultado.getSinopsis());
    }
    
}
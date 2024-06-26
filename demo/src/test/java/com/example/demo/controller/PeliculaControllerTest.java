package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaServicelmpl;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaServicelmpl peliculaServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{
        //Arrange
        //Creación de Peliculas
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1);
        pelicula1.setTitulo("Titanic");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1997);
        java.sql.Date yearDate = new java.sql.Date(calendar.getTimeInMillis());

        pelicula1.setYear(yearDate);
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

       // Creación de lista de Películas
       List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
       when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].titulo", Matchers.is("Titanic")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].year", Matchers.is("1997"))) 
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].director", Matchers.is("James Cameron")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].genero", Matchers.is("Romance")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[0].sinopsis", Matchers.is("Un joven artista y una joven aristócrata se enamoran a bordo del primer y mítico viaje del RMS Titanic.")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].titulo", Matchers.is("Avatar")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].year", Matchers.is("2009"))) 
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].director", Matchers.is("James Cameron")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].genero", Matchers.is("Ciencia Ficción")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculaList[1].sinopsis", Matchers.is("En el año 2154, Jake Sully, un ex-marine condenado a vivir en una silla de ruedas, sigue siendo un auténtico guerrero.")));

        
    }
    
}

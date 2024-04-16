package com.example.demo.service;

import com.example.demo.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Integer id);
    //METODOS NUEVO CRUD
    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Integer id, Pelicula pelicula);
    void deletePelicula(Integer id);
    
}

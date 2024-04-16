package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServicelmpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPeliculas(){
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Integer id){
        return peliculaRepository.findById(id);
    }
    //CRUD
    @Override
    public Pelicula createPelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Integer id, Pelicula pelicula){
        if(peliculaRepository.existsById(id)){
           pelicula.setId(id);
           return peliculaRepository.save(pelicula);
        } else {
         return null;
        }
    }

    @Override
    public void deletePelicula(Integer id){
        peliculaRepository.deleteById(id);
    }

}

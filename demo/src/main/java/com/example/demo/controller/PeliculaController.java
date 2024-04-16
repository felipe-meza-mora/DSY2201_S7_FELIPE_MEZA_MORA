package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.getAllPeliculas();
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable int id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        
        if (pelicula.isPresent()) {
            return ResponseEntity.ok(pelicula.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este ID, no existe en nuestros registros.");
        }
    }

    //CONTROLADORES NUEVO CRUD

    @PostMapping
    public Pelicula creatPelicula(@RequestBody Pelicula pelicula){
        return peliculaService.createPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula updatPelicula(@PathVariable Integer id, @RequestBody Pelicula pelicula){
        return peliculaService.updatePelicula(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Integer id){
        peliculaService.deletePelicula(id);
    }
    
    
    
}

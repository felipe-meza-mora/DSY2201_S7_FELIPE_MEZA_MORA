package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();

        List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
                .map(pelicula -> EntityModel.of(pelicula,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel()
                        ))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculaResources, linkTo.withRel("peliculas"));

        return resources;
    }

     @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable int id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        
        if (pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
        } else {
            throw new PeliculaNotFoundException("Este ID, no existe en nuestros registros.");
        }
    }

    //CONTROLADORES NUEVO CRUD

    @PostMapping
    public EntityModel<Pelicula> creatPelicula(@RequestBody Pelicula pelicula){
        Pelicula createPelicula =  peliculaService.createPelicula(pelicula);
        return EntityModel.of(createPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createPelicula.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }

    @PutMapping("/{id}")
    public EntityModel<Pelicula> updatPelicula(@PathVariable Integer id, @RequestBody Pelicula pelicula){
        Pelicula updatePelicula = peliculaService.updatePelicula(id, pelicula);
        return EntityModel.of(updatePelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Integer id){
        peliculaService.deletePelicula(id);
    }
    
    
    
}

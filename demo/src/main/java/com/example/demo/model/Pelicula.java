package com.example.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name= "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="titulo")
    private String titulo;

    @JsonFormat(pattern = "yyyy")
    @Column(name = "year")
    private Date year;

    @Column(name = "director")
    private String director;

    @Column(name = "genero")
    private String genero;

    @Column(name = "sinopsis", length = 1000)
    private String sinopsis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year){
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis){
        this.sinopsis = sinopsis;
    }
    
}

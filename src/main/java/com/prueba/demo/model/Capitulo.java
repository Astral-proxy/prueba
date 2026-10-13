package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Capitulos")
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_capitulo")
    private Integer idCapitulo;

    @Column(name = "numero_capitulo", nullable = false, unique = true)
    private Integer numeroCapitulo; // Ej: 1, 2, 3...

    @Column(name = "titulo", length = 150)
    private String titulo; // Ej: "El Despertar del Vapor"

    @Column(name = "resumen", columnDefinition = "TEXT")
    private String resumen; // De qué trata este episodio/capítulo

    // Relación: Muchos personajes pueden aparecer en muchos capítulos
    @ManyToMany(mappedBy = "capitulosAparicion")
    @JsonIgnore
    private List<Personaje> personajes;

    public Capitulo() {}

    public Capitulo(Integer numeroCapitulo, String titulo, String resumen) {
        this.numeroCapitulo = numeroCapitulo;
        this.titulo = titulo;
        this.resumen = resumen;
    }

    // Getters y Setters
    public Integer getIdCapitulo() { return idCapitulo; }
    public void setIdCapitulo(Integer idCapitulo) { this.idCapitulo = idCapitulo; }
    public Integer getNumeroCapitulo() { return numeroCapitulo; }
    public void setNumeroCapitulo(Integer numeroCapitulo) { this.numeroCapitulo = numeroCapitulo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
    public List<Personaje> getPersonajes() { return personajes; }
    public void setPersonajes(List<Personaje> personajes) { this.personajes = personajes; }
}
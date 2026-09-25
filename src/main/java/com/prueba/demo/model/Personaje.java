package com.prueba.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Personajes")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje")
    private Integer idPersonaje;

    // Especificamos el tamaño y evitamos que quede vacio con (nullable = false)
    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "ocupacion", length = 50)
    private String ocupacion;

    // Especificamos que es un texto largo, no un texto corto normal
    @Column(name = "biografia", columnDefinition = "TEXT")
    private String biografia;

    // Constructor

    // Constructor vacío (para que Spring Boot funcione)
    public Personaje() {
    }

    // Constructor con datos (Para facilitar la creación desde el código)
    public Personaje(String nombreCompleto, String ocupacion, String biografia) {
        this.nombreCompleto = nombreCompleto;
        this.ocupacion = ocupacion;
        this.biografia = biografia;
    }

    // Getters and Setters

    public Integer getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
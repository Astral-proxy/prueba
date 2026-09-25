package com.prueba.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Personajes")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje")
    private Integer idPersonaje;

    // Le decimos exactamente cuánto mide y que no puede ir vacío (nullable = false)
    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "ocupacion", length = 50)
    private String ocupacion;

    // Le especificamos que es un texto largo, no un texto corto normal
    @Column(name = "biografia", columnDefinition = "TEXT")
    private String biografia;

    /* ==============================================================
       CONSTRUCTORES (Necesarios para crear los objetos)
       ============================================================== */

    // 1. Constructor vacío (Obligatorio para que Spring Boot funcione)
    public Personaje() {
    }

    // 2. Constructor con datos (Para facilitar la creación desde el código)
    public Personaje(String nombreCompleto, String ocupacion, String biografia) {
        this.nombreCompleto = nombreCompleto;
        this.ocupacion = ocupacion;
        this.biografia = biografia;
    }

    /* ==============================================================
       GETTERS Y SETTERS (Las puertas de acceso)
       ============================================================== */

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
package com.prueba.demo.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especie")
    private Integer idEspecie;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre; // Ej: "Humano", "Autómata", "Cíborg"

    @Column(name = "caracteristicas", columnDefinition = "TEXT")
    private String caracteristicas; // Qué los hace especiales o sus debilidades

    // Relación: Una especie engloba a muchos personajes
    @OneToMany(mappedBy = "especie")
    @JsonIgnore // Evita el bucle infinito al consultar
    private List<Personaje> personajes;

    public Especie() {}

    public Especie(String nombre, String caracteristicas) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }

    // Getters y Setters
    public Integer getIdEspecie() { return idEspecie; }
    public void setIdEspecie(Integer idEspecie) { this.idEspecie = idEspecie; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
    public List<Personaje> getPersonajes() { return personajes; }
    public void setPersonajes(List<Personaje> personajes) { this.personajes = personajes; }
}

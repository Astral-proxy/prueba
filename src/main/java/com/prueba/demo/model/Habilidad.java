package com.prueba.demo.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Habilidades")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidad")
    private Integer idHabilidad;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo_habilidad", length = 50)
    private String tipoHabilidad; // Ej: "Pasiva", "Ofensiva", "Tecnológica", "Magia"

    @Column(name = "efecto", columnDefinition = "TEXT")
    private String efecto; // Descripción de lo que hace

    // Relación: Muchos personajes pueden aprender muchas habilidades (Muchos a Muchos)
    @ManyToMany(mappedBy = "habilidades")
    @JsonIgnore
    private List<Personaje> personajes;

    public Habilidad() {}

    public Habilidad(String nombre, String tipoHabilidad, String efecto) {
        this.nombre = nombre;
        this.tipoHabilidad = tipoHabilidad;
        this.efecto = efecto;
    }

    // Getters y Setters
    public Integer getIdHabilidad() { return idHabilidad; }
    public void setIdHabilidad(Integer idHabilidad) { this.idHabilidad = idHabilidad; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipoHabilidad() { return tipoHabilidad; }
    public void setTipoHabilidad(String tipoHabilidad) { this.tipoHabilidad = tipoHabilidad; }
    public String getEfecto() { return efecto; }
    public void setEfecto(String efecto) { this.efecto = efecto; }
    public List<Personaje> getPersonajes() { return personajes; }
    public void setPersonajes(List<Personaje> personajes) { this.personajes = personajes; }
}

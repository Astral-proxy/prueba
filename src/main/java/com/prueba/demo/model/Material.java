package com.prueba.demo.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMaterial;

    @Column(nullable = false, length = 100)
    private String nombre; // Ej: "Éter", "Cobre Refinado", "Carbón Ígneo"

    @Column(columnDefinition = "TEXT")
    private String propiedades;

    // Relación: Muchos personajes pueden tener muchos materiales en su inventario
    @ManyToMany(mappedBy = "inventarioMateriales")
    @JsonIgnore
    private List<Personaje> personajes;

    public Material() {}

    // Getters y Setters
    public Integer getIdMaterial() { return idMaterial; }
    public void setIdMaterial(Integer idMaterial) { this.idMaterial = idMaterial; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPropiedades() { return propiedades; }
    public void setPropiedades(String propiedades) { this.propiedades = propiedades; }
    public List<Personaje> getPersonajes() { return personajes; }
    public void setPersonajes(List<Personaje> personajes) { this.personajes = personajes; }
}
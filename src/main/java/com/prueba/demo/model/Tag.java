package com.prueba.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "Tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Integer idTag;

    @Column(name = "nombre_tag", unique = true)
    private String nombre; // Ej: "Ingeniero", "Granjero"

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Personaje> personajes;

    public Tag() {}

    // Getters y Setters
    public Integer getIdTag() { return idTag; }
    public void setIdTag(Integer idTag) { this.idTag = idTag; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Personaje> getPersonajes() { return personajes; }
    public void setPersonajes(List<Personaje> personajes) { this.personajes = personajes; }
}
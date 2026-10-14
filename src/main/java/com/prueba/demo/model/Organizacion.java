package com.prueba.demo.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Organizaciones")
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganizacion;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String ideologia;

    @ManyToMany(mappedBy = "organizaciones")
    @JsonIgnore
    private List<Personaje> miembros;

    // Getters y Setters
    public Integer getIdOrganizacion() { return idOrganizacion; }
    public void setIdOrganizacion(Integer idOrganizacion) { this.idOrganizacion = idOrganizacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getIdeologia() { return ideologia; }
    public void setIdeologia(String ideologia) { this.ideologia = ideologia; }
    public List<Personaje> getMiembros() { return miembros; }
    public void setMiembros(List<Personaje> miembros) { this.miembros = miembros; }
}

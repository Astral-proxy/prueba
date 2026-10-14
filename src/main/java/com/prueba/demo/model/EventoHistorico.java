package com.prueba.demo.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Eventos_Historicos")
public class EventoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 50)
    private String eraOAno; // Ej: "Año 205 después de la Caída"

    @Column(columnDefinition = "TEXT")
    private String resumen;

    @ManyToMany(mappedBy = "eventos")
    @JsonIgnore
    private List<Personaje> personajesInvolucrados;

    // Getters y Setters
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEraOAno() { return eraOAno; }
    public void setEraOAno(String eraOAno) { this.eraOAno = eraOAno; }
    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
    public List<Personaje> getPersonajesInvolucrados() { return personajesInvolucrados; }
    public void setPersonajesInvolucrados(List<Personaje> personajesInvolucrados) { this.personajesInvolucrados = personajesInvolucrados; }
}

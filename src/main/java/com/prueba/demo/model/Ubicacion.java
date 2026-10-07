package com.prueba.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "Ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    @Column(name = "nombre_lugar", nullable = false, unique = true, length = 100)
    private String nombreLugar;

    @Column(name = "tipo_lugar", length = 50)
    private String tipoLugar; // Ej: "Ciudad", "Continente", "Edificio", "Ruinas"

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    // Relación: Una ubicación puede tener muchos personajes residiendo o nacidos ahí
    @OneToMany(mappedBy = "ubicacionActual")
    @JsonIgnore // Evitamos el bucle infinito
    private List<Personaje> personajesResidentes;

    // Constructores
    public Ubicacion() {}

    public Ubicacion(String nombreLugar, String tipoLugar, String descripcion) {
        this.nombreLugar = nombreLugar;
        this.tipoLugar = tipoLugar;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdUbicacion() { return idUbicacion; }
    public void setIdUbicacion(Integer idUbicacion) { this.idUbicacion = idUbicacion; }
    public String getNombreLugar() { return nombreLugar; }
    public void setNombreLugar(String nombreLugar) { this.nombreLugar = nombreLugar; }
    public String getTipoLugar() { return tipoLugar; }
    public void setTipoLugar(String tipoLugar) { this.tipoLugar = tipoLugar; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Personaje> getPersonajesResidentes() { return personajesResidentes; }
    public void setPersonajesResidentes(List<Personaje> personajesResidentes) { this.personajesResidentes = personajesResidentes; }
}
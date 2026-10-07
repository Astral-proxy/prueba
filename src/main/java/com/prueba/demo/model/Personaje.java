package com.prueba.demo.model;

import jakarta.persistence.*;
import java.util.List;

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

    // Un personaje tiene MUCHAS relaciones
    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<Relacion> relacionesFamiliares;

    // No olvides agregar el Getter y Setter para 'relacionesFamiliares' más abajo

    @ManyToMany
    @JoinTable(
        name = "personaje_tag", // El nombre de la tabla intermedia en Postgres
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;

    // Recuerda agregar su Getter y Setter correspondiente

    // La brújula que apunta a la ubicación donde está el personaje
    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacionActual;

    // (Recuerda agregar al final del archivo su respectivo getUbicacionActual() y setUbicacionActual() )

    // Constructores

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

    // Para las relaciones familiares (Uno a Muchos)
    public List<Relacion> getRelacionesFamiliares() {
        return relacionesFamiliares;
    }

    public void setRelacionesFamiliares(List<Relacion> relacionesFamiliares) {
        this.relacionesFamiliares = relacionesFamiliares;
    }

    // Para los Tags / Profesiones (Muchos a Muchos)
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    // Para la Ubicación (Muchos a Uno)
    public Ubicacion getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(Ubicacion ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }
}
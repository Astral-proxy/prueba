package com.prueba.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Artefactos")
public class Artefacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArtefacto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50)
    private String rareza; // Ej: "Común", "Legendario", "Prototipo"

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relación: Un personaje (portador) tiene muchos artefactos
    @ManyToOne
    @JoinColumn(name = "id_portador")
    private Personaje portador;

    public Artefacto() {}

    // Getters y Setters
    public Integer getIdArtefacto() { return idArtefacto; }
    public void setIdArtefacto(Integer idArtefacto) { this.idArtefacto = idArtefacto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRareza() { return rareza; }
    public void setRareza(String rareza) { this.rareza = rareza; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Personaje getPortador() { return portador; }
    public void setPortador(Personaje portador) { this.portador = portador; }
}
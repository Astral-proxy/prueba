package com.prueba.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    @Column(nullable = false, length = 100)
    private String nombre; // Ej: "Acorazado Volador Ícaro"

    @Column(length = 50)
    private String tipo; // Ej: "Dirigible", "Tren", "Submarino"

    @Column
    private Integer capacidadPasajeros;

    // Relación: Un personaje (propietario o capitán) tiene muchos vehículos
    @ManyToOne
    @JoinColumn(name = "id_propietario")
    private Personaje propietario;

    public Vehiculo() {}

    // Getters y Setters
    public Integer getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(Integer idVehiculo) { this.idVehiculo = idVehiculo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(Integer capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }
    public Personaje getPropietario() { return propietario; }
    public void setPropietario(Personaje propietario) { this.propietario = propietario; }
}
package com.prueba.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Relaciones")
public class Relacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacion")
    private Integer idRelacion;

    @Column(name = "tipo_relacion", nullable = false)
    private String tipoRelacion; // Ej: "Pareja", "Hijo/a", "Cónyuge"

    @Column(name = "estado", nullable = false)
    private String estado; // Ej: "Vivo", "Muerto", "Desaparecido", "Desconocido"

    @Column(name = "nombre_familiar")
    private String nombreFamiliar; // El nombre del pariente

    // La "cuerda" que amarra esta relación con la tabla Personajes
    @ManyToOne
    @JoinColumn(name = "id_personaje") // Esta es la llave foránea en Postgres
    @JsonIgnore // Evita un error de bucle infinito al mostrar los datos en la web
    private Personaje personaje;

    public Relacion() {}

    // Getters y Setters...
    public Integer getIdRelacion() { return idRelacion; }
    public void setIdRelacion(Integer idRelacion) { this.idRelacion = idRelacion; }
    public String getTipoRelacion() { return tipoRelacion; }
    public void setTipoRelacion(String tipoRelacion) { this.tipoRelacion = tipoRelacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNombreFamiliar() { return nombreFamiliar; }
    public void setNombreFamiliar(String nombreFamiliar) { this.nombreFamiliar = nombreFamiliar; }
    public Personaje getPersonaje() { return personaje; }
    public void setPersonaje(Personaje personaje) { this.personaje = personaje; }
}
package com.prueba.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "Artefactos")
public class Artefacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artefacto")
    private Integer idArtefacto;

    @Column(name = "slug", unique = true, nullable = false, length = 100)
    private String slug;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", length = 50)
    private String tipo; // Ej: "Arma", "Accesorio", "Autómata", "Reliquia"

    @Column(name = "fecha_creacion", length = 80)
    private String fechaCreacion; // Ej: "Convención Universitaria", "Año 1894"

    @Column(name = "peso_kg")
    private Double pesoKg;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    // Relación Muchos a Muchos: Un artefacto puede tener uno o varios dueños
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "artefacto_dueno",
        joinColumns = @JoinColumn(name = "id_artefacto"),
        inverseJoinColumns = @JoinColumn(name = "id_personaje")
    )
    @JsonIgnoreProperties({
        "relaciones", "tags", "habilidades", "inventarioMateriales",
        "capitulosAparicion", "organizaciones", "eventos", "documentosEscritos", "biografia"
    })
    private List<Personaje> duenos = new ArrayList<>();

    // Relación Muchos a Muchos: Materiales que componen el artefacto
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "artefacto_material",
        joinColumns = @JoinColumn(name = "id_artefacto"),
        inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    @JsonIgnoreProperties("personajes")
    private List<Material> materiales = new ArrayList<>();

    public Artefacto() {}

    public Artefacto(String slug, String nombre, String tipo, String fechaCreacion, Double pesoKg, String descripcion) {
        this.slug = slug;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
        this.pesoKg = pesoKg;
        this.descripcion = descripcion;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================
    public Integer getIdArtefacto() { return idArtefacto; }
    public void setIdArtefacto(Integer idArtefacto) { this.idArtefacto = idArtefacto; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Double getPesoKg() { return pesoKg; }
    public void setPesoKg(Double pesoKg) { this.pesoKg = pesoKg; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Personaje> getDuenos() { return duenos; }
    public void setDuenos(List<Personaje> duenos) { this.duenos = duenos; }

    public List<Material> getMateriales() { return materiales; }
    public void setMateriales(List<Material> materiales) { this.materiales = materiales; }
}
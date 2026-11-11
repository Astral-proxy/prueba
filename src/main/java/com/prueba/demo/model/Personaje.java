package com.prueba.demo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Personajes")
public class Personaje {

    // ==========================================
    // 1. LLAVES Y DATOS PRINCIPALES
    // ==========================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje")
    private Integer idPersonaje;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "alias", length = 100)
    private String alias;

    @Column(name = "ocupacion", length = 50)
    private String ocupacion;

    @Column(name = "biografia", columnDefinition = "TEXT")
    private String biografia;

    // ==========================================
    // 2. EXPEDIENTE TÉCNICO / FÍSICO
    // ==========================================
    @Column(name = "edad")
    private Integer edad;

    @Column(name = "estatura_cm")
    private Integer estaturaCm;

    @Column(name = "estado_civil", length = 50)
    private String estadoCivil;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    // ==========================================
    // 3. VÍNCULOS NARRATIVOS (SUB-TABLA LIGERA)
    // ==========================================
    @ElementCollection
    @CollectionTable(name = "personaje_relaciones", joinColumns = @JoinColumn(name = "personaje_id"))
    @MapKeyColumn(name = "nombre_personaje_relacionado")
    @Column(name = "tipo_vinculo")
    private Map<String, String> relaciones = new HashMap<>();

    // ==========================================
    // 4. RELACIONES JPA COMPLEJAS (FOREING KEYS)
    // ==========================================
    
    @ManyToOne
    @JoinColumn(name = "id_especie")
    @JsonIgnoreProperties("personajes") // Evita bucle infinito si Especie llama a Personaje
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    @JsonIgnoreProperties("personajes")
    private Ubicacion ubicacionActual;

    @ManyToMany
    @JoinTable(
        name = "personaje_tag",
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    @JsonIgnoreProperties("personajes")
    private List<Tag> tags;

    @ManyToMany
    @JoinTable(
        name = "personaje_habilidad",
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_habilidad")
    )
    @JsonIgnoreProperties("personajes")
    private List<Habilidad> habilidades;

    @ManyToMany
    @JoinTable(
        name = "personaje_material",
        joinColumns = @JoinColumn(name = "personaje_id"),
        inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    @JsonIgnoreProperties("personajes")
    private List<Material> inventarioMateriales;

    @ManyToMany
    @JoinTable(
        name = "personaje_capitulo",
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_capitulo")
    )
    @JsonIgnoreProperties("personajes")
    private List<Capitulo> capitulosAparicion;

    @ManyToMany
    @JoinTable(
        name = "personaje_organizacion",
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_organizacion")
    )
    @JsonIgnoreProperties("miembros")
    private List<Organizacion> organizaciones;

    @ManyToMany
    @JoinTable(
        name = "personaje_evento",
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_evento")
    )
    @JsonIgnoreProperties("participantes")
    private List<EventoHistorico> eventos;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore // El frontend no necesita traerse todos los textos enteros aquí
    private List<Documento> documentosEscritos;

    // ==========================================
    // 5. CONSTRUCTORES
    // ==========================================
    public Personaje() {}

    public Personaje(String nombreCompleto, String ocupacion, String biografia) {
        this.nombreCompleto = nombreCompleto;
        this.ocupacion = ocupacion;
        this.biografia = biografia;
    }

    // ==========================================
    // 6. GETTERS Y SETTERS
    // ==========================================
    public Integer getIdPersonaje() { return idPersonaje; }
    public void setIdPersonaje(Integer idPersonaje) { this.idPersonaje = idPersonaje; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getBiografia() { return biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public Integer getEstaturaCm() { return estaturaCm; }
    public void setEstaturaCm(Integer estaturaCm) { this.estaturaCm = estaturaCm; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Map<String, String> getRelaciones() { return relaciones; }
    public void setRelaciones(Map<String, String> relaciones) { this.relaciones = relaciones; }

    public Especie getEspecie() { return especie; }
    public void setEspecie(Especie especie) { this.especie = especie; }

    public Ubicacion getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(Ubicacion ubicacionActual) { this.ubicacionActual = ubicacionActual; }

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public List<Habilidad> getHabilidades() { return habilidades; }
    public void setHabilidades(List<Habilidad> habilidades) { this.habilidades = habilidades; }

    public List<Material> getInventarioMateriales() { return inventarioMateriales; }
    public void setInventarioMateriales(List<Material> inventarioMateriales) { this.inventarioMateriales = inventarioMateriales; }

    public List<Capitulo> getCapitulosAparicion() { return capitulosAparicion; }
    public void setCapitulosAparicion(List<Capitulo> capitulosAparicion) { this.capitulosAparicion = capitulosAparicion; }

    public List<Organizacion> getOrganizaciones() { return organizaciones; }
    public void setOrganizaciones(List<Organizacion> organizaciones) { this.organizaciones = organizaciones; }

    public List<EventoHistorico> getEventos() { return eventos; }
    public void setEventos(List<EventoHistorico> eventos) { this.eventos = eventos; }

    public List<Documento> getDocumentosEscritos() { return documentosEscritos; }
    public void setDocumentosEscritos(List<Documento> documentosEscritos) { this.documentosEscritos = documentosEscritos; }
}
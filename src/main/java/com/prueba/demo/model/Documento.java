package com.prueba.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 50)
    private String tipo; // Ej: "Plano", "Diario", "Manifiesto"

    @Column(columnDefinition = "TEXT")
    private String contenido;

    // Relación: Un personaje (autor) escribe muchos documentos
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Personaje autor;

    // Getters y Setters
    public Integer getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Integer idDocumento) { this.idDocumento = idDocumento; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public Personaje getAutor() { return autor; }
    public void setAutor(Personaje autor) { this.autor = autor; }
}

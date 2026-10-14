package com.prueba.demo.service;

import com.prueba.demo.model.Documento;
import com.prueba.demo.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> obtenerPorId(Integer id) {
        return documentoRepository.findById(id);
    }

    public Documento guardar(Documento documento) {
        return documentoRepository.save(documento);
    }

    public Documento actualizar(Integer id, Documento detalles) {
        return documentoRepository.findById(id).map(doc -> {
            doc.setTitulo(detalles.getTitulo());
            doc.setTipo(detalles.getTipo());
            doc.setContenido(detalles.getContenido());
            doc.setAutor(detalles.getAutor());
            return documentoRepository.save(doc);
        }).orElseThrow(() -> new RuntimeException("Documento no encontrado con ID: " + id));
    }

    public void eliminar(Integer id) {
        documentoRepository.deleteById(id);
    }
}
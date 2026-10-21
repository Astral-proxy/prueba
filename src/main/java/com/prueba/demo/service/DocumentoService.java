package com.prueba.demo.service;

import com.prueba.demo.model.Documento;
import com.prueba.demo.repository.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> obtenerPorId(Integer id) {
        return documentoRepository.findById(id);
    }

    // Cambiado de "guardarDocumento" a "guardar" para que coincida con el Controller
    public Documento guardar(Documento documento) {
        return documentoRepository.save(documento);
    }

    // Agregamos la función "actualizar" que pedía el Controller
    public Documento actualizar(Integer id, Documento documentoActualizado) {
        if (documentoRepository.existsById(id)) {
            documentoActualizado.setIdDocumento(id);
            return documentoRepository.save(documentoActualizado);
        }
        return null;
    }

    // Agregamos la función "eliminar" que pedía el Controller
    public void eliminar(Integer id) {
        documentoRepository.deleteById(id);
    }
}
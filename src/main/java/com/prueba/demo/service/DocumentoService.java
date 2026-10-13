package com.steampunk.wiki.service;

import com.steampunk.wiki.model.Documento;
import com.steampunk.wiki.repository.DocumentoRepository;
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

    public Optional<Documento> obtenerPorId(Long id) {
        return documentoRepository.findById(id);
    }

    public Documento guardarDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

    public void eliminarDocumento(Long id) {
        documentoRepository.deleteById(id);
    }
}
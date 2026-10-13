package com.steampunk.wiki.controller;

import com.steampunk.wiki.model.Documento;
import com.steampunk.wiki.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<Documento> listarTodos() {
        return documentoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Documento> obtenerPorId(@PathVariable Long id) {
        return documentoService.obtenerPorId(id);
    }

    @PostMapping
    public Documento crearDocumento(@RequestBody Documento documento) {
        return documentoService.guardarDocumento(documento);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);
    }
}
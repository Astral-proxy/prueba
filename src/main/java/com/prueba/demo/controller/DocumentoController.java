package com.prueba.demo.controller;

import com.prueba.demo.model.Documento;
import com.prueba.demo.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Documento> obtenerPorId(@PathVariable Integer id) {
        return documentoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Documento crear(@RequestBody Documento documento) {
        return documentoService.guardar(documento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizar(@PathVariable Integer id, @RequestBody Documento documento) {
        try {
            return ResponseEntity.ok(documentoService.actualizar(id, documento));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        documentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
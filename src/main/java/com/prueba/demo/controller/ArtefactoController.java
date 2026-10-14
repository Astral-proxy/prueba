package com.prueba.demo.controller;

import com.prueba.demo.model.Artefacto;
import com.prueba.demo.service.ArtefactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artefactos")
public class ArtefactoController {

    @Autowired
    private ArtefactoService artefactoService;

    @GetMapping
    public List<Artefacto> listarTodos() {
        return artefactoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artefacto> obtenerPorId(@PathVariable Integer id) {
        return artefactoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Artefacto crear(@RequestBody Artefacto artefacto) {
        return artefactoService.guardar(artefacto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artefacto> actualizar(@PathVariable Integer id, @RequestBody Artefacto artefacto) {
        try {
            return ResponseEntity.ok(artefactoService.actualizar(id, artefacto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        artefactoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
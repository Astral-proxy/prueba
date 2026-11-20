package com.prueba.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.model.Artefacto;
import com.prueba.demo.repository.ArtefactoRepository;

@RestController
@RequestMapping("/api/artefactos")
@CrossOrigin(origins = "http://localhost:5173")
public class ArtefactoController {

    private final ArtefactoRepository artefactoRepository;

    public ArtefactoController(ArtefactoRepository artefactoRepository) {
        this.artefactoRepository = artefactoRepository;
    }

    @GetMapping
    public List<Artefacto> listarTodos() {
        return artefactoRepository.findAll();
    }

    @GetMapping("/{idOSlug}")
    public ResponseEntity<Artefacto> obtenerDetalle(@PathVariable String idOSlug) {
        if (idOSlug.matches("\\d+")) {
            return artefactoRepository.findById(Integer.parseInt(idOSlug))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        return artefactoRepository.findBySlugIgnoreCase(idOSlug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
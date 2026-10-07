package com.prueba.demo.controller;

import com.prueba.demo.model.Tag;
import com.prueba.demo.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags") // La ruta web para buscar profesiones/etiquetas
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // 1. Mostrar todos los tags disponibles
    @GetMapping
    public List<Tag> obtenerTodos() {
        return tagService.obtenerTodos();
    }

    // 2. Buscar un tag específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Tag> obtenerPorId(@PathVariable Integer id) {
        Optional<Tag> tag = tagService.obtenerPorId(id);
        
        if (tag.isPresent()) {
            return ResponseEntity.ok(tag.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. Crear un nuevo tag desde la página web
    @PostMapping
    public Tag guardarTag(@RequestBody Tag tag) {
        return tagService.guardarTag(tag);
    }
}
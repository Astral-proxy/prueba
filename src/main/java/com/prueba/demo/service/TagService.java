package com.prueba.demo.service;

import com.prueba.demo.model.Tag;
import com.prueba.demo.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // 1. Ver todos los tags creados
    public List<Tag> obtenerTodos() {
        return tagRepository.findAll();
    }

    // 2. Buscar un tag por su ID
    public Optional<Tag> obtenerPorId(Integer id) {
        return tagRepository.findById(id);
    }

    // 3. Buscar un tag por su nombre
    public Optional<Tag> obtenerPorNombre(String nombre) {
        return tagRepository.findByNombre(nombre);
    }

    // 4. Crear un nuevo tag (Ej. "Mecánico de Vapor")
    public Tag guardarTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
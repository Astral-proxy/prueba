package com.prueba.demo.service;

import com.prueba.demo.model.Artefacto;
import com.prueba.demo.repository.ArtefactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtefactoService {

    @Autowired
    private ArtefactoRepository artefactoRepository;

    public List<Artefacto> obtenerTodos() {
        return artefactoRepository.findAll();
    }

    public Optional<Artefacto> obtenerPorId(Integer id) {
        return artefactoRepository.findById(id);
    }

    public Artefacto guardar(Artefacto artefacto) {
        return artefactoRepository.save(artefacto);
    }

    public Artefacto actualizar(Integer id, Artefacto detalles) {
        return artefactoRepository.findById(id).map(art -> {
            art.setNombre(detalles.getNombre());
            art.setRareza(detalles.getRareza());
            art.setDescripcion(detalles.getDescripcion());
            art.setPortador(detalles.getPortador());
            return artefactoRepository.save(art);
        }).orElseThrow(() -> new RuntimeException("Artefacto no encontrado con ID: " + id));
    }

    public void eliminar(Integer id) {
        artefactoRepository.deleteById(id);
    }
}
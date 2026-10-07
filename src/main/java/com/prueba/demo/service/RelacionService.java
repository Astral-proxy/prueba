package com.prueba.demo.service;

import com.prueba.demo.model.Relacion;
import com.prueba.demo.repository.RelacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelacionService {

    private final RelacionRepository relacionRepository;

    public RelacionService(RelacionRepository relacionRepository) {
        this.relacionRepository = relacionRepository;
    }

    // Mostrar todas las relaciones registradas en el mundo
    public List<Relacion> obtenerTodas() {
        return relacionRepository.findAll();
    }

    // Buscar una relación específica
    public Optional<Relacion> obtenerPorId(Integer id) {
        return relacionRepository.findById(id);
    }

    // Buscar el árbol familiar completo de un solo personaje
    public List<Relacion> obtenerFamiliaDePersonaje(Integer idPersonaje) {
        return relacionRepository.findByPersonaje_IdPersonaje(idPersonaje);
    }

    // Registrar un nuevo familiar
    public Relacion guardarRelacion(Relacion relacion) {
        return relacionRepository.save(relacion);
    }
}
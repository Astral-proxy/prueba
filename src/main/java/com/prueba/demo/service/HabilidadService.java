package com.prueba.demo.service;

import com.prueba.demo.model.Habilidad;
import com.prueba.demo.repository.HabilidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;

    public HabilidadService(HabilidadRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    public List<Habilidad> obtenerTodas() {
        return habilidadRepository.findAll();
    }

    public Optional<Habilidad> obtenerPorId(Integer id) {
        return habilidadRepository.findById(id);
    }

    public Habilidad guardarHabilidad(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }
}

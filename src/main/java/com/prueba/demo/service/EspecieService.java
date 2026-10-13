package com.prueba.demo.service;

import com.prueba.demo.model.Especie;
import com.prueba.demo.repository.EspecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieService(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    public List<Especie> obtenerTodas() {
        return especieRepository.findAll();
    }

    public Optional<Especie> obtenerPorId(Integer id) {
        return especieRepository.findById(id);
    }

    public Especie guardarEspecie(Especie especie) {
        return especieRepository.save(especie);
    }
}

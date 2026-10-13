package com.prueba.demo.service;

import com.prueba.demo.model.Capitulo;
import com.prueba.demo.repository.CapituloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapituloService {

    private final CapituloRepository capituloRepository;

    public CapituloService(CapituloRepository capituloRepository) {
        this.capituloRepository = capituloRepository;
    }

    public List<Capitulo> obtenerTodos() {
        return capituloRepository.findAll();
    }

    public Optional<Capitulo> obtenerPorId(Integer id) {
        return capituloRepository.findById(id);
    }

    public Optional<Capitulo> obtenerPorNumero(Integer numero) {
        return capituloRepository.findByNumeroCapitulo(numero);
    }

    public Capitulo guardarCapitulo(Capitulo capitulo) {
        return capituloRepository.save(capitulo);
    }
}

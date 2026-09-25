package com.prueba.demo.service;

import com.prueba.demo.model.Personaje;
import com.prueba.demo.repository.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    // Llamamos a repository para poder usar sus métodos (findAll, findById, save, etc.)
    private final PersonajeRepository personajeRepository;

    // Inyectamos las dependencias
    public PersonajeService(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    /* ==============================================================
       REGLAS DE NEGOCIO (Las acciones permitidas en la Wiki)
       ============================================================== */

    // Lista de los personajes registrados
    public List<Personaje> obtenerTodos() {
        return personajeRepository.findAll();
    }

    // Buscar a un personaje en específico
    public Optional<Personaje> obtenerPorId(Integer id) {
        return personajeRepository.findById(id);
    }

    // Guardar un personaje nuevo en la base de datos
    public Personaje guardarPersonaje(Personaje personaje) {
        // Aquí podremos agregar nuevas reglas a futuro, por ejemplo:
        // if (personaje.getNombreCompleto() == null) { lanzar un error... }
        return personajeRepository.save(personaje);
    }
}
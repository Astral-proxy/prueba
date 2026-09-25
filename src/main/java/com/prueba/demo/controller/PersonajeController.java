package com.prueba.demo.controller;

import com.prueba.demo.model.Personaje;
import com.prueba.demo.service.PersonajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    // Llamamos a service para poder usar sus métodos (obtenerTodos, obtenerPorId, guardarPersonaje, etc.)
    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    /* ==============================================================
       RUTAS WEB (Los enlaces a los que entrará la página)
       ============================================================== */

    // Mostrar la lista de personajes (Ej. /api/personajes para ver todos los personajes)
    @GetMapping
    public List<Personaje> obtenerTodos() {
        return personajeService.obtenerTodos();
    }

    // Buscar un personaje en específico (Ej. /api/personajes/1 para ver el personaje con id 1)
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPorId(@PathVariable Integer id) {
        Optional<Personaje> personaje = personajeService.obtenerPorId(id);
        
        if (personaje.isPresent()) {
            return ResponseEntity.ok(personaje.get()); // Responde 200 OK con el personaje encontrado
        } else {
            return ResponseEntity.notFound().build(); // Responde 404 Not Found si no se encuentra el personaje
        }
    }

    // Crear un nuevo personaje (Ej. /api/personajes con método POST para crear un personaje)
    @PostMapping
    public Personaje guardarPersonaje(@RequestBody Personaje personaje) {
        return personajeService.guardarPersonaje(personaje);
    }
}
package com.prueba.demo.controller;

import com.prueba.demo.model.Relacion;
import com.prueba.demo.service.RelacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/relaciones")
public class RelacionController {

    private final RelacionService relacionService;

    public RelacionController(RelacionService relacionService) {
        this.relacionService = relacionService;
    }

    @GetMapping
    public List<Relacion> obtenerTodas() {
        return relacionService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relacion> obtenerPorId(@PathVariable Integer id) {
        Optional<Relacion> relacion = relacionService.obtenerPorId(id);
        
        if (relacion.isPresent()) {
            return ResponseEntity.ok(relacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ¡La ruta especial! Ej: /api/relaciones/familia/1 (trae la familia de Ansus)
    @GetMapping("/familia/{idPersonaje}")
    public List<Relacion> obtenerFamilia(@PathVariable Integer idPersonaje) {
        return relacionService.obtenerFamiliaDePersonaje(idPersonaje);
    }

    @PostMapping
    public Relacion guardarRelacion(@RequestBody Relacion relacion) {
        return relacionService.guardarRelacion(relacion);
    }
}
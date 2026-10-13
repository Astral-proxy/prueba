package com.example.demo.controller;

import com.example.demo.model.Habilidad;
import com.example.demo.service.HabilidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadController {

    private final HabilidadService habilidadService;

    public HabilidadController(HabilidadService habilidadService) {
        this.habilidadService = habilidadService;
    }

    @GetMapping
    public List<Habilidad> obtenerTodas() {
        return habilidadService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> obtenerPorId(@PathVariable Integer id) {
        Optional<Habilidad> habilidad = habilidadService.obtenerPorId(id);
        
        return habilidad.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habilidad guardarHabilidad(@RequestBody Habilidad habilidad) {
        return habilidadService.guardarHabilidad(habilidad);
    }
}
package com.prueba.demo.controller;

import com.prueba.demo.model.Especie;
import com.prueba.demo.service.EspecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {

    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public List<Especie> obtenerTodas() {
        return especieService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> obtenerPorId(@PathVariable Integer id) {
        Optional<Especie> especie = especieService.obtenerPorId(id);
        
        return especie.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Especie guardarEspecie(@RequestBody Especie especie) {
        return especieService.guardarEspecie(especie);
    }
}

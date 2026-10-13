package com.prueba.demo.controller;

import com.prueba.demo.model.Capitulo;
import com.prueba.demo.service.CapituloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/capitulos")
public class CapituloController {

    private final CapituloService capituloService;

    public CapituloController(CapituloService capituloService) {
        this.capituloService = capituloService;
    }

    @GetMapping
    public List<Capitulo> obtenerTodos() {
        return capituloService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Capitulo> obtenerPorId(@PathVariable Integer id) {
        Optional<Capitulo> capitulo = capituloService.obtenerPorId(id);
        
        return capitulo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ruta especial: /api/capitulos/numero/1
    @GetMapping("/numero/{numero}")
    public ResponseEntity<Capitulo> obtenerPorNumero(@PathVariable Integer numero) {
        Optional<Capitulo> capitulo = capituloService.obtenerPorNumero(numero);
        
        return capitulo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Capitulo guardarCapitulo(@RequestBody Capitulo capitulo) {
        return capituloService.guardarCapitulo(capitulo);
    }
}

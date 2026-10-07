package com.prueba.demo.controller;

import com.prueba.demo.model.Ubicacion;
import com.prueba.demo.service.UbicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ubicaciones") // Esta es la ruta web para el mapa
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    // 1. Mostrar todos los lugares del mundo Steampunk
    @GetMapping
    public List<Ubicacion> obtenerTodas() {
        return ubicacionService.obtenerTodas();
    }

    // 2. Buscar un lugar específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> obtenerPorId(@PathVariable Integer id) {
        Optional<Ubicacion> ubicacion = ubicacionService.obtenerPorId(id);
        
        if (ubicacion.isPresent()) {
            return ResponseEntity.ok(ubicacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. Crear un nuevo lugar desde la página web
    @PostMapping
    public Ubicacion guardarUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.guardarUbicacion(ubicacion);
    }
}
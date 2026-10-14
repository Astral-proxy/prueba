package com.prueba.demo.controller;

import com.prueba.demo.model.EventoHistorico;
import com.prueba.demo.service.EventoHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos-historicos")
public class EventoHistoricoController {

    @Autowired
    private EventoHistoricoService eventoHistoricoService;

    @GetMapping
    public List<EventoHistorico> listarTodos() {
        return eventoHistoricoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoHistorico> obtenerPorId(@PathVariable Integer id) {
        return eventoHistoricoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EventoHistorico crear(@RequestBody EventoHistorico evento) {
        return eventoHistoricoService.guardar(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoHistorico> actualizar(@PathVariable Integer id, @RequestBody EventoHistorico evento) {
        try {
            return ResponseEntity.ok(eventoHistoricoService.actualizar(id, evento));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        eventoHistoricoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
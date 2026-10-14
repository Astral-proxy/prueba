package com.prueba.demo.service;

import com.prueba.demo.model.EventoHistorico;
import com.prueba.demo.repository.EventoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoHistoricoService {

    @Autowired
    private EventoHistoricoRepository eventoHistoricoRepository;

    public List<EventoHistorico> obtenerTodos() {
        return eventoHistoricoRepository.findAll();
    }

    public Optional<EventoHistorico> obtenerPorId(Integer id) {
        return eventoHistoricoRepository.findById(id);
    }

    public EventoHistorico guardar(EventoHistorico evento) {
        return eventoHistoricoRepository.save(evento);
    }

    public EventoHistorico actualizar(Integer id, EventoHistorico detalles) {
        return eventoHistoricoRepository.findById(id).map(evento -> {
            evento.setNombre(detalles.getNombre());
            evento.setEraOAno(detalles.getEraOAno());
            evento.setResumen(detalles.getResumen());
            if (detalles.getPersonajesInvolucrados() != null) {
                evento.setPersonajesInvolucrados(detalles.getPersonajesInvolucrados());
            }
            return eventoHistoricoRepository.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento histórico no encontrado con ID: " + id));
    }

    public void eliminar(Integer id) {
        eventoHistoricoRepository.deleteById(id);
    }
}
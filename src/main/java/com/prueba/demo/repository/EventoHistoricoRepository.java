package com.prueba.demo.repository;

import com.example.demo.model.EventoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoHistoricoRepository extends JpaRepository<EventoHistorico, Integer> {
}

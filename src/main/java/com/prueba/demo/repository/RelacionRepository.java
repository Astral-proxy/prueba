package com.prueba.demo.repository;

import com.prueba.demo.model.Relacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RelacionRepository extends JpaRepository<Relacion, Integer> {
    
    // Magia de Spring: Busca todas las relaciones familiares que pertenezcan a un personaje específico
    List<Relacion> findByPersonaje_IdPersonaje(Integer idPersonaje);
}
package com.prueba.demo.repository;

import com.prueba.demo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    
    // Herramienta extra: Buscar si ya existe el tag "Ingeniero" en la base de datos
    Optional<Tag> findByNombre(String nombre);
}
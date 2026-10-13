package com.example.demo.repository;

import com.example.demo.model.Capitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CapituloRepository extends JpaRepository<Capitulo, Integer> {
    
    // Herramienta extra: Buscar un capítulo directamente por su número (Ej. Capítulo 3)
    Optional<Capitulo> findByNumeroCapitulo(Integer numeroCapitulo);
}
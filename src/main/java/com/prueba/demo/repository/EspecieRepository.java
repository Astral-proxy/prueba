package com.prueba.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.demo.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer> {
    
    // Con esta línea, Spring Boot ya sabe buscar especies por su nombre
    Optional<Especie> findByNombre(String nombre);
}
package com.prueba.demo.repository;

import com.prueba.demo.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
    // Al igual que con Personaje, Spring Boot ya sabe hacer todo el CRUD (Crear, Leer, Actualizar, Borrar)
    // solo con heredar de JpaRepository.
}
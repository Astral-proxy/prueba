package com.prueba.demo.repository;

import com.prueba.demo.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Esta anotación indica que esta interfaz es un repositorio de Spring Data JPA
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
}
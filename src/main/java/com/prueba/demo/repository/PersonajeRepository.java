package com.prueba.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.demo.model.Personaje;

@Repository // Esta anotación indica que esta interfaz es un repositorio de Spring Data JPA
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    
    // Spring Boot lee el nombre de este método y automáticamente crea el código SQL 
    // para buscar todos los personajes que tengan un Tag con el nombre que le pases.
    List<Personaje> findByTags_Nombre(String nombreTag);

    // NUEVO: Método para que el Seeder verifique si el personaje ya existe antes de inyectarlo
    boolean existsByNombreCompleto(String nombreCompleto);
}
package com.prueba.demo.repository;

import com.prueba.demo.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // Esta anotación indica que esta interfaz es un repositorio de Spring Data JPA
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    
    // Spring Boot lee el nombre de este método y automáticamente crea el código SQL 
    // para buscar todos los personajes que tengan un Tag con el nombre que le pases.
    List<Personaje> findByTags_Nombre(String nombreTag);
}
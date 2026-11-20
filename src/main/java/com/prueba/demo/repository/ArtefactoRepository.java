package com.prueba.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.demo.model.Artefacto;

@Repository
public interface ArtefactoRepository extends JpaRepository<Artefacto, Integer> {
    Optional<Artefacto> findBySlugIgnoreCase(String slug);
    boolean existsBySlug(String slug);
}
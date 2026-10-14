package com.prueba.demo.repository;

import com.prueba.demo.model.Artefacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtefactoRepository extends JpaRepository<Artefacto, Integer> {
}

package com.prueba.demo.service;

import com.prueba.demo.model.Organizacion;
import com.prueba.demo.repository.OrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizacionService {

    @Autowired
    private OrganizacionRepository organizacionRepository;

    public List<Organizacion> obtenerTodas() {
        return organizacionRepository.findAll();
    }

    public Optional<Organizacion> obtenerPorId(Integer id) {
        return organizacionRepository.findById(id);
    }

    public Organizacion guardar(Organizacion organizacion) {
        return organizacionRepository.save(organizacion);
    }

    public Organizacion actualizar(Integer id, Organizacion organizacionDetalles) {
        return organizacionRepository.findById(id).map(organizacion -> {
            organizacion.setNombre(organizacionDetalles.getNombre());
            organizacion.setIdeologia(organizacionDetalles.getIdeologia());
            if (organizacionDetalles.getMiembros() != null) {
                organizacion.setMiembros(organizacionDetalles.getMiembros());
            }
            return organizacionRepository.save(organizacion);
        }).orElseThrow(() -> new RuntimeException("Organización no encontrada con ID: " + id));
    }

    public void eliminar(Integer id) {
        organizacionRepository.deleteById(id);
    }
}

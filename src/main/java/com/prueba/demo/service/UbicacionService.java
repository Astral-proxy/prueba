package com.example.demo.service;

import com.example.demo.model.Ubicacion;
import com.example.demo.repository.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    // Conectamos con el archivista
    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    // 1. Obtener el mapa completo (todas las ubicaciones)
    public List<Ubicacion> obtenerTodas() {
        return ubicacionRepository.findAll();
    }

    // 2. Buscar un lugar específico por su ID
    public Optional<Ubicacion> obtenerPorId(Integer id) {
        return ubicacionRepository.findById(id);
    }

    // 3. Registrar una nueva ciudad, edificio o ruina en la base de datos
    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }
}
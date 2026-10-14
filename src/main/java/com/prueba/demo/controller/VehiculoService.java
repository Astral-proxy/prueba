package com.prueba.demo.service;

import com.prueba.demo.model.Vehiculo;
import com.prueba.demo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> obtenerPorId(Integer id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo actualizar(Integer id, Vehiculo detalles) {
        return vehiculoRepository.findById(id).map(veh -> {
            veh.setNombre(detalles.getNombre());
            veh.setTipo(detalles.getTipo());
            veh.setCapacidadPasajeros(detalles.getCapacidadPasajeros());
            veh.setPropietario(detalles.getPropietario());
            return vehiculoRepository.save(veh);
        }).orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }

    public void eliminar(Integer id) {
        vehiculoRepository.deleteById(id);
    }
}
package com.prueba.demo.seeder;

import com.prueba.demo.model.*;
import com.prueba.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PersonajeRepository personajeRepository;
    private final EspecieRepository especieRepository;

    public DataSeeder(PersonajeRepository personajeRepository, EspecieRepository especieRepository) {
        this.personajeRepository = personajeRepository;
        this.especieRepository = especieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Iniciando el sembrado inteligente desde datos_wiki.csv...");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("datos_wiki.csv").getInputStream()))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) { 
                    esPrimeraLinea = false; 
                    continue; 
                }

                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String nombreCompleto = datos[0].trim();
                String alias = datos[1].trim();
                if (nombreCompleto.isEmpty()) continue; 

                // ESCENARIO 3: Verificamos si el personaje NO existe en la BD antes de continuar
                if (!personajeRepository.existsByNombreCompleto(nombreCompleto)) {
                    
                    String ocupacion = datos[2].trim();
                    String nombreEspecie = datos[3].trim();
                    String edadStr = datos[4].trim();
                    String estaturaStr = datos[5].trim();
                    String estadoCivil = datos[6].trim();
                    String fechaNacimiento = datos[7].trim();
                    String biografia = datos[8].replace("\"", "").trim();

                    // Crear al Personaje
                    Personaje personaje = new Personaje(nombreCompleto, ocupacion, biografia);
                    
                    if (!alias.isEmpty()) personaje.setAlias(alias);
                    if (!estadoCivil.isEmpty()) personaje.setEstadoCivil(estadoCivil);
                    if (!fechaNacimiento.isEmpty()) personaje.setFechaNacimiento(fechaNacimiento);

                    // Bloque seguro para convertir los números
                    try {
                        if (!edadStr.isEmpty()) personaje.setEdad(Integer.valueOf(edadStr));
                        if (!estaturaStr.isEmpty()) personaje.setEstaturaCm(Integer.valueOf(estaturaStr));
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Aviso: La edad o estatura de " + nombreCompleto + " no es válida. Se dejará en blanco.");
                    }

                    // Conectar la Especie
                    if (!nombreEspecie.isEmpty()) {
                        Especie especie = especieRepository.findByNombre(nombreEspecie)
                                .orElseGet(() -> especieRepository.save(new Especie(nombreEspecie, "Autogenerado desde el Excel")));
                        personaje.setEspecie(especie);
                    }

                    // Guardar en PostgreSQL
                    personajeRepository.save(personaje);
                    System.out.println("✅ Nuevo personaje sembrado: " + nombreCompleto);

                } else {
                    // Si ya existe, lo ignoramos para evitar duplicados o errores
                    System.out.println("⏩ Omitiendo: " + nombreCompleto + " ya existe en la base de datos.");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }
}
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
    // Más adelante agregaremos aquí los repositorios de Artefactos, Facciones, etc.

    public DataSeeder(PersonajeRepository personajeRepository, EspecieRepository especieRepository) {
        this.personajeRepository = personajeRepository;
        this.especieRepository = especieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Candado de seguridad: Si ya hay personajes, no volver a sembrar
        if (personajeRepository.count() > 0) {
            System.out.println("🌱 La base de datos ya tiene personajes. Omitiendo el Seeder.");
            return;
        }

        System.out.println("🚀 Iniciando el sembrado de datos desde datos_wiki.csv...");

        // 2. Leer el archivo CSV desde la carpeta resources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("datos_wiki.csv").getInputStream()))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) { 
                    esPrimeraLinea = false; 
                    continue; // Saltamos los encabezados de las columnas
                }

                // Magia de Expresiones Regulares para separar por comas (ignorando comas dentro de comillas)
                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // 3. Extraer columnas (El orden depende de tu CSV)
                String nombreCompleto = datos[0].trim();
                String alias = datos[1].trim();
                if (nombreCompleto.isEmpty()) continue; // Evitar filas vacías

                String ocupacion = datos[2].trim();
                String nombreEspecie = datos[3].trim();
                String edadStr = datos[4].trim();
                String estaturaStr = datos[5].trim();
                String estadoCivil = datos[6].trim();
                String fechaNacimiento = datos[7].trim();
                String biografia = datos[8].replace("\"", "").trim(); // Quitamos comillas si las tiene

                // 4. Crear al Personaje
                Personaje personaje = new Personaje(nombreCompleto, ocupacion, biografia);
                
                if (!alias.isEmpty()) personaje.setAlias(alias);
                if (!edadStr.isEmpty()) personaje.setEdad(Integer.parseInt(edadStr));
                if (!estaturaStr.isEmpty()) personaje.setEstaturaCm(Integer.parseInt(estaturaStr));
                if (!estadoCivil.isEmpty()) personaje.setEstadoCivil(estadoCivil);
                if (!fechaNacimiento.isEmpty()) personaje.setFechaNacimiento(fechaNacimiento);

                // Bloque seguro para convertir los números (Complaciendo al editor)
                try {
                    if (edadStr != null && !edadStr.isEmpty()) {
                        personaje.setEdad(Integer.valueOf(edadStr));
                    }
                    if (estaturaStr != null && !estaturaStr.isEmpty()) {
                        personaje.setEstaturaCm(Integer.valueOf(estaturaStr));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Aviso: La edad o estatura de " + nombreCompleto + " no es válida. Se dejará en blanco.");
                }

                // 5. Conectar la Especie (Buscamos si existe, si no, la creamos)
                if (!nombreEspecie.isEmpty()) {
                    Especie especie = especieRepository.findByNombre(nombreEspecie)
                            .orElseGet(() -> especieRepository.save(new Especie(nombreEspecie, "Autogenerado desde el Excel")));
                    personaje.setEspecie(especie);
                }

                // 6. Guardar en PostgreSQL
                personajeRepository.save(personaje);
                System.out.println("✅ Personaje sembrado exitosamente: " + nombreCompleto);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }
}
package com.prueba.demo.seeder;

import com.prueba.demo.model.Artefacto;
import com.prueba.demo.model.Material;
import com.prueba.demo.model.Personaje;
import com.prueba.demo.repository.ArtefactoRepository;
import com.prueba.demo.repository.MaterialRepository;
import com.prueba.demo.repository.PersonajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Order(2)
public class ArtefactoSeeder implements CommandLineRunner {

    private final ArtefactoRepository artefactoRepository;
    private final PersonajeRepository personajeRepository;
    private final MaterialRepository materialRepository;

    public ArtefactoSeeder(ArtefactoRepository artefactoRepository,
                           PersonajeRepository personajeRepository,
                           MaterialRepository materialRepository) {
        this.artefactoRepository = artefactoRepository;
        this.personajeRepository = personajeRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public void run(String... args) {
        ClassPathResource recurso = new ClassPathResource("datos_artefactos.csv");
        if (!recurso.exists()) {
            System.out.println("⚠️ No se encontró datos_artefactos.csv en resources. Omitiendo sembrado de artefactos.");
            return;
        }

        System.out.println("⚙️ Iniciando sembrado de artefactos desde datos_artefactos.csv...");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(recurso.getInputStream(), StandardCharsets.UTF_8))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String nombre = datos[0].trim();
                if (nombre.isEmpty()) continue;

                String slug = generarSlug(nombre);

                if (!artefactoRepository.existsBySlug(slug)) {
                    String tipo = datos.length > 1 ? datos[1].trim() : "";
                    String fechaCreacion = datos.length > 2 ? datos[2].trim() : "";
                    String pesoStr = datos.length > 3 ? datos[3].trim() : "";
                    String duenosStr = datos.length > 4 ? datos[4].trim() : "";
                    String materialesStr = datos.length > 5 ? datos[5].trim() : "";
                    String descripcion = datos.length > 6 ? datos[6].replace("\"", "").trim() : "";

                    Double pesoKg = null;
                    try {
                        if (!pesoStr.isEmpty()) pesoKg = Double.valueOf(pesoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Peso inválido para el artefacto: " + nombre);
                    }

                    Artefacto artefacto = new Artefacto(slug, nombre, tipo, fechaCreacion, pesoKg, descripcion);

                    // 1. Vincular Dueños (separados por ';')
                    if (!duenosStr.isEmpty()) {
                        List<Personaje> listaDuenos = new ArrayList<>();
                        for (String nombreDueno : duenosStr.split(";")) {
                            String limpio = nombreDueno.trim();
                            if (limpio.isEmpty()) continue;

                            Optional<Personaje> p = personajeRepository.findByAliasIgnoreCase(limpio)
                                    .or(() -> personajeRepository.findByNombreCompletoIgnoreCase(limpio));

                            if (p.isPresent()) {
                                listaDuenos.add(p.get());
                            } else {
                                System.out.println("⚠️ Dueño no encontrado en BD para " + nombre + ": " + limpio);
                            }
                        }
                        artefacto.setDuenos(listaDuenos);
                    }

                    // 2. Vincular o Crear Materiales (separados por ';')
                    if (!materialesStr.isEmpty()) {
                        List<Material> listaMateriales = new ArrayList<>();
                        for (String nombreMat : materialesStr.split(";")) {
                            String limpio = nombreMat.trim();
                            if (limpio.isEmpty()) continue;

                            Material material = materialRepository.findByNombreIgnoreCase(limpio)
                                    .orElseGet(() -> {
                                        Material nuevo = new Material();
                                        nuevo.setNombre(limpio);
                                        nuevo.setPropiedades("Registrado desde CSV de artefactos");
                                        return materialRepository.save(nuevo);
                                    });
                            listaMateriales.add(material);
                        }
                        artefacto.setMateriales(listaMateriales);
                    }

                    artefactoRepository.save(artefacto);
                    System.out.println("✅ Artefacto sembrado: " + nombre + " (/" + slug + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al leer datos_artefactos.csv: " + e.getMessage());
        }
    }

    private String generarSlug(String texto) {
        String sinAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return sinAcentos.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-");
    }
}
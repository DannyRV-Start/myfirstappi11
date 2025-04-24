// LocationService.java
package co.edu.umanizales.myfirstapi.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final List<String> ubications = new ArrayList<>();

    public LocationService() {
        loadUbicationsFromFile();
    }

    private void loadUbicationsFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("DIVIPOLA-_C_digos_municipios_20250423.csv").getInputStream(),
                        StandardCharsets.UTF_8))) {
            String line;
            // Cargar todas las líneas incluyendo encabezado
            while ((line = reader.readLine()) != null) {
                ubications.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el archivo CSV: " + e.getMessage(), e);
        }
    }

    // Todos los registros
    public List<String> getUbicationsFromFile() {
        return new ArrayList<>(ubications);
    }

    // Filtra por código de departamento (campo 0)
    public List<String> getUbicationsByDepartmentCode(String code) {
        return ubications.stream()
                .filter(line -> !line.startsWith("Código Departamento") && line.split(",")[0].equalsIgnoreCase(code))
                .collect(Collectors.toList());
    }

    // Filtra por nombre de municipio (campo 3)
    public List<String> getUbicationsByName(String name) {
        return ubications.stream()
                .filter(line -> {
                    if (line.startsWith("Código Departamento")) return false;
                    String[] parts = line.split(",");
                    return parts[3].equalsIgnoreCase(name);
                })
                .collect(Collectors.toList());
    }

    // Filtra municipios que inician con letters (campo 3)
    public List<String> getUbicationsByInitialLetters(String letters) {
        return ubications.stream()
                .filter(line -> {
                    if (line.startsWith("Código Departamento")) return false;
                    String[] parts = line.split(",");
                    return parts[3].toLowerCase().startsWith(letters.toLowerCase());
                })
                .collect(Collectors.toList());
    }

    // Filtra por nombre de departamento (campo 1)
    public List<String> getUbicationsByState(String stateName) {
        return ubications.stream()
                .filter(line -> {
                    if (line.startsWith("Código Departamento")) return false;
                    String[] parts = line.split(",");
                    return parts[1].equalsIgnoreCase(stateName);
                })
                .collect(Collectors.toList());
    }

    // Busca un registro por código de municipio (campo 2)
    public String getUbicationByMunicipalityCode(String mCode) {
        return ubications.stream()
                .filter(line -> !line.startsWith("Código Departamento") && line.split(",")[2].equalsIgnoreCase(mCode))
                .findFirst().orElse(null);
    }

    // Lista única de departamentos ("code,name")
    public List<String> getStates() {
        return ubications.stream()
                .filter(line -> !line.startsWith("Código Departamento"))
                .map(line -> {
                    String[] parts = line.split(",");
                    return parts[0] + "," + parts[1];
                })
                .distinct()
                .collect(Collectors.toList());
    }

    // Nombre de departamento por código
    public String getStateByCode(String code) {
        return ubications.stream()
                .filter(line -> !line.startsWith("Código Departamento") && line.split(",")[0].equalsIgnoreCase(code))
                .map(line -> line.split(",")[1])
                .findFirst().orElse(null);
    }

    // Municipios capitales: código municipio termina en 001
    public List<String> getCapitals() {
        return ubications.stream()
                .filter(line -> {
                    if (line.startsWith("Código Departamento")) return false;
                    String[] parts = line.split(",");
                    return parts[2].endsWith("001");
                })
                .collect(Collectors.toList());
    }
}
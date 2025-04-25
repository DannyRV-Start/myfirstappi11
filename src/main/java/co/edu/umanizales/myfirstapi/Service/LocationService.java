package co.edu.umanizales.myfirstapi.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            while ((line = reader.readLine()) != null) {
                ubications.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el archivo CSV: " + e.getMessage(), e);
        }
    }

    public List<String> getUbicationsFromFile() {
        return new ArrayList<>(ubications);
    }

    public List<String> getUbicationsByDepartmentCode(String code) {
        List<String> result = new ArrayList<>();
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts[0].equalsIgnoreCase(code)) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> getUbicationsByName(String name) {
        List<String> result = new ArrayList<>();
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 3 && parts[3].equalsIgnoreCase(name)) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> getUbicationsByInitialLetters(String letters) {
        List<String> result = new ArrayList<>();
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 3 && parts[3].toLowerCase().startsWith(letters.toLowerCase())) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> getUbicationsByState(String stateName) {
        List<String> result = new ArrayList<>();
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 1 && parts[1].equalsIgnoreCase(stateName)) {
                result.add(line);
            }
        }
        return result;
    }

    public String getUbicationByMunicipalityCode(String mCode) {
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 2 && parts[2].equalsIgnoreCase(mCode)) {
                return line;
            }
        }
        return null;
    }

    public List<String> getStates() {
        Set<String> states = new HashSet<>();
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 1) {
                states.add(parts[0] + "," + parts[1]);
            }
        }
        return new ArrayList<>(states);
    }

    public String getStateByCode(String code) {
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 1 && parts[0].equalsIgnoreCase(code)) {
                return parts[1];
            }
        }
        return null;
    }

    public List<String> getCapitals() {
        List<String> result = new ArrayList<>();
        for (String line : ubications) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 2 && parts[2].endsWith("001")) {
                result.add(line);
            }
        }
        return result;
    }
}

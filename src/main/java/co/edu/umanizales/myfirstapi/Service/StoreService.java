package co.edu.umanizales.myfirstapi.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final List<String> stores = new ArrayList<>();

    public StoreService() {
        loadStoresFromFile();
    }

    private void loadStoresFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("STORE.csv").getInputStream(),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stores.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el archivo STORE.csv: " + e.getMessage(), e);
        }
    }

    public List<String> getAllStores() {
        return new ArrayList<>(stores);
    }
}
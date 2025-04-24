package co.edu.umanizales.myfirstapi.Controller;

import co.edu.umanizales.myfirstapi.Service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubications")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/mostrar")
    public List<String> mostrarUbicaciones() {
        return locationService.getUbicationsFromFile();
    }

    @GetMapping("/by-department-code/{code}")
    public List<String> getByDepartmentCode(@PathVariable String code) {
        return locationService.getUbicationsByDepartmentCode(code);
    }

    @GetMapping("/by-name/{name}")
    public List<String> getByName(@PathVariable String name) {
        return locationService.getUbicationsByName(name);
    }

    @GetMapping("/by-initials/{letters}")
    public List<String> getByInitials(@PathVariable String letters) {
        return locationService.getUbicationsByInitialLetters(letters);
    }

    @GetMapping("/by-state/{stateName}")
    public List<String> getByState(@PathVariable String stateName) {
        return locationService.getUbicationsByState(stateName);
    }

    @GetMapping("/by-municipality-code/{mCode}")
    public String getByMunicipalityCode(@PathVariable String mCode) {
        return locationService.getUbicationByMunicipalityCode(mCode);
    }

    @GetMapping("/states")
    public List<String> getStates() {
        return locationService.getStates();
    }

    @GetMapping("/state-by-code/{code}")
    public String getStateByCode(@PathVariable String code) {
        return locationService.getStateByCode(code);
    }

    @GetMapping("/capitals")
    public List<String> getCapitals() {
        return locationService.getCapitals();
    }
}

package co.edu.umanizales.myfirstapi.Controller;

import co.edu.umanizales.myfirstapi.Service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/listar")
    public List<String> listarStores() {
        return storeService.getAllStores();
    }
}
package co.edu.umanizales.myfirstapi.Controller;

import co.edu.umanizales.myfirstapi.Model.Seller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping(path = "/Seller")

public class SellerController{
    @GetMapping()
    public List<Seller> getSellers(){
        Seller Camilo=new Seller(7514441, "Camilo", "Ortiz" ,"Masculino", (byte)37, 320551356, "hola", "Manizales");
        Seller Esteban=new Seller(116667, "Esteban", "Restrepo", "Masculino", (byte)43, 315558189, "hola", "Pereira");
        Seller Maria=new Seller(12003, "Maria", "Cardona", "Feminine", (byte)25,311657666, "hola", "Manizales");
        Seller Sofia=new Seller(142536, "Sofia", "Estrada", "Feminine", (byte)37, 315455218, "hola", "Medellin");
        Seller Tomas=new Seller(100566, "Tomas", "Osorio", "Masculino", (byte)40, 315226872, "hola",  "Pereira");

        return Arrays.asList(Camilo, Esteban, Maria, Sofia, Tomas);
    }
}


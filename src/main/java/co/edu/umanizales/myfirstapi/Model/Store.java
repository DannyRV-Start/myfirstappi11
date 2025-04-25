package co.edu.umanizales.myfirstapi.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store {
    private String code;
    private String description;
    private String location;  // código del municipio
    private String address;

    public Store(String code, String description, String location, String address) {
        this.code = code;
        this.description = description;
        this.location = location;
        this.address = address;
    }
}

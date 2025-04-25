package co.edu.umanizales.myfirstapi.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String code;
    private String description;

    public Location(String code, String description, String locPart, String part, String s, String string, String trim) {
        this.code = code;
        this.description = description;
    }

}



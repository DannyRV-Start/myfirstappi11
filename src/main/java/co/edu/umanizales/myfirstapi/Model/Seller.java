package co.edu.umanizales.myfirstapi.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seller {
    private int identification;
    private String name;
    private String lastname;
    private String gender;
    private byte age;
    private int phone;
    private String address;
    private String ubication;

    public Seller(int identification, String name, String lastname, String gender, byte age, int phone, String address, String ubication) {
        this.identification = identification;
        this.name = name;
        this.lastname= lastname;
        this.gender= gender;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.ubication= ubication;
    }

    void update(int identification, String name, String lastname, String gender, byte age, int phone, String address, String ubication) {
        this.identification = identification;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.ubication= ubication;
    }
}


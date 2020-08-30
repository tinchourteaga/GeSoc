package API.DTOs;

import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;

public class ZipCodeDTO {


    private Provincia state;
    private Pais country;
    private int zipCode;
    private Ciudad city;

    public ZipCodeDTO(Provincia state, Pais country, int zipCode, Ciudad city) {
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Provincia getState() {
        return state;
    }

    public Pais getCountry() {
        return country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public Ciudad getCity() {
        return city;
    }
}

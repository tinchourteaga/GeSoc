package API.DTOs;

public class ZipCodeDTO {


    private NombreYID state;
    private NombreYID country;
    private int zipCode;
    private NombreYID city;

    public ZipCodeDTO(NombreYID state, NombreYID country, int zipCode, NombreYID city) {
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
    }

    public NombreYID getState() {
        return state;
    }

    public NombreYID getCountry() {
        return country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public NombreYID getCity() {
        return city;
    }
}

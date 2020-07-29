package Dominio.Entidad;

public class Direccion {
    private String calle;
    private String altura;
    private String piso;

    public Direccion(String calle, String altura, String piso) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
    }

    public String getCalle() {
        return calle;
    }

    public String getAltura() {
        return altura;
    }

    public String getPiso() {
        return piso;
    }
}

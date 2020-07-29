package Dominio.Entidad;

public class DireccionPostal {
    private Direccion direccion;
    private int cp;
    private String pais;
    private String provincia;
    private String ciudad;

    public DireccionPostal(Direccion direccion, int cp, String pais, String provincia, String ciudad) {
        this.direccion = direccion;
        this.cp = cp;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public int getCp() {
        return cp;
    }

    public String getPais() {
        return pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }
}

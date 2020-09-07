package Dominio.Entidad;

import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;


public class DireccionPostal {
    private Direccion direccion;
    private int cp;
    private Pais pais;
    private Provincia provincia;
    private Ciudad ciudad;

    public DireccionPostal(Direccion direccion, int cp, Pais pais) {

        this.direccion = direccion;
        this.cp = cp;
        this.pais = pais;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}

package Dominio.Entidad;

import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;

import javax.persistence.*;

@Embeddable
public class DireccionPostal {
    @Embedded
    private Direccion direccion;

    @Column(name = "codigo_postal")
    private int cp;

    @Transient
    private Pais pais;

    @Transient
    private Provincia provincia;

    @Transient
    private Ciudad ciudad;

    public DireccionPostal(Direccion direccion, int cp, Pais pais, Provincia provincia, Ciudad ciudad) {
        this.direccion = direccion;
        this.cp = cp;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
    }

    protected DireccionPostal() { }

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

    public Pais getPais() { return pais; }

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

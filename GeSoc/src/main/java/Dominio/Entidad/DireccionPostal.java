package Dominio.Entidad;

import API.ML.DTOs.CiudadDTO;
import API.ML.DTOs.PaisDTO;
import API.ML.DTOs.ProvinciaDTO;

import javax.persistence.*;

@Embeddable
public class DireccionPostal {
    @Embedded
    private Direccion direccion;

    @Transient
    private int cp;

    @Transient
    private PaisDTO pais;

    @Transient
    private ProvinciaDTO provincia;

    @OneToOne
    @JoinColumn(name = "ciudad")
    private CiudadDTO ciudad;

    public DireccionPostal(Direccion direccion, int cp, PaisDTO pais) {

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

    public PaisDTO getPais() { return pais; }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

    public ProvinciaDTO getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaDTO provincia) {
        this.provincia = provincia;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }
}

package Dominio.Entidad;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private String altura;

    @Column(name = "piso")
    private String piso;

    @Column(name = "departamento")
    private String departamento;

    public Direccion(String calle, String altura, String piso, String departamento) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.departamento = departamento;
    }

    protected Direccion() { }

    public String getCalle() {
        return calle;
    }

    public String getAltura() {
        return altura;
    }

    public String getPiso() {
        return piso;
    }

    public String getDepartamento() {
        return departamento;
    }
}

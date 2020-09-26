package Dominio.Egreso.Core;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class MetodoDePago {
    @Column(name = "metodo_pago")
    @Enumerated(value = EnumType.STRING)
    private TipoDeMedioDePago tipo;

    @Column(name = "descripcion_metodo_pago")
    private String descripcion;

    public MetodoDePago(TipoDeMedioDePago tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public TipoDeMedioDePago getTipo() {
        return tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }
}

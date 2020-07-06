package Dominio.Egreso.Core;

public class MetodoDePago {

    private TipoDeMedioDePago tipo;
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

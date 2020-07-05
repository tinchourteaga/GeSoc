package Dominio.Egreso.Core;

public class Detalle {
    private float valor;
    private String descripcion;

    public Detalle(float valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public float getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

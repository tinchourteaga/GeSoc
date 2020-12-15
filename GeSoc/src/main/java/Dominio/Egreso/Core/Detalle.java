package Dominio.Egreso.Core;

import javax.persistence.*;

@Entity
@Table(name = "dom_detalle_presupuestos")
public class Detalle {
    @Id
    @GeneratedValue
    private int detalle;

    @Column(name = "valor")
    private float valor;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presupuesto")
    private Presupuesto presupuesto;

    public Detalle() { }

    public Detalle(float valor, String descripcion,Integer cantidad) {
        this.valor = valor;
        this.descripcion = descripcion;
        this.cantidad=cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public float getValor() {
        return valor;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
}

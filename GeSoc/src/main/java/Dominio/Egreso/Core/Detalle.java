package Dominio.Egreso.Core;

import javax.persistence.*;

@Entity
@Table(name = "dom_detalle_presupuestos", uniqueConstraints = @UniqueConstraint(columnNames = {"detalle", "presupuesto"}))
public class Detalle {
    @Id
    @GeneratedValue
    private int detalle;

    @Column(name = "valor")
    private float valor;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "presupuesto", referencedColumnName = "presupuesto")
    private Presupuesto presupuesto;

    public Detalle() { }

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
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
}

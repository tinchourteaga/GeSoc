package Dominio.Egreso.Core;

import javax.persistence.*;

@Entity
@Table(name = "dom_items")
public class Item {
    @Id
    @GeneratedValue
    private int item;

    @ManyToOne
    @JoinColumn(name = "egreso", referencedColumnName = "egreso")
    private Egreso egreso;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private Integer cantidad;


    public Item() { }

    public Item(Float importe,String desc,Integer cantidad){
        this.valor=importe;
        this.descripcion=desc;
        this.cantidad=cantidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public Float getValor() {
        return valor;
    }
    public String getDescripcion() {
        return descripcion;
    }
}


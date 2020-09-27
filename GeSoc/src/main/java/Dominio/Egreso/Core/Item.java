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

    public Item(Float importe,String desc){
        this.valor=importe;
        this.descripcion=desc;
    }

    public Float getValor() {
        return valor;
    }
    public String getDescripcion() {
        return descripcion;
    }
}


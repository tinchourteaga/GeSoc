package Dominio.Egreso.Core;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dom_metodo_pago")
public class MetodoDePago {
    @Id
    @GeneratedValue
    private int metodo_pago;

    @Column(name = "tipo_metodo_pago")
    @Enumerated(value = EnumType.STRING)
    private TipoDeMedioDePago tipo;

    @Column(name = "descripcion_metodo_pago")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "metodo_pago")
    List<Egreso> egresos = new ArrayList<>();;

    public MetodoDePago() { }

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

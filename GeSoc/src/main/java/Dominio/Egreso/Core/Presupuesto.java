package Dominio.Egreso.Core;

import Converters.LocalDateAttributeConverter;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dom_presupuestos")
public class Presupuesto {

    @Id
    @GeneratedValue
    private int presupuesto;

    @OneToMany(mappedBy = "criterio", cascade = CascadeType.ALL)
    private List<Criterio> criterios;

    @Column(name = "valor")
    private float valor;

    @Column(name = "fecha_creado")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha;

    @OneToMany(mappedBy = "detalle", cascade = CascadeType.ALL)
    private List<Detalle> detalles;

    @OneToOne
    @JoinColumn(name = "documento_comercial")
    private DocumentoComercial documentoComercial;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "proveedor")
    private Proveedor proveedor;

    public Presupuesto() { }

    public Presupuesto(List<Criterio> criterios, float valor, List<Detalle> detalles, DocumentoComercial documentoComercial, Proveedor proveedor) {
        this.criterios = criterios;
        this.valor = valor;
        this.detalles = detalles;
        this.documentoComercial = documentoComercial;
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor(){return this.proveedor;}
    public List<Criterio> getCriterios() { return criterios; }
    public float getValor() { return valor;}
    public List<Detalle> getDetalles() {return detalles;}
    public DocumentoComercial getDocumentoComercial() { return documentoComercial;}
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setDetalles(List<Detalle> detalles) { this.detalles = detalles; }
    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

}

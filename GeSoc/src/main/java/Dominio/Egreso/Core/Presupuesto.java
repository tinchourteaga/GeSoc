package Dominio.Egreso.Core;

import Converters.LocalDateAttributeConverter;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dom_presupuestos")
public class Presupuesto {

    @Id
    @GeneratedValue
    private int presupuesto;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dom_presupuestos_dom_categoria",
            joinColumns = { @JoinColumn(name = "presupuesto") },
            inverseJoinColumns = { @JoinColumn(name = "categoria") }
    )
    private List<CategoriaCriterio> categorias;

    @Column(name = "valor")
    private float valor;

    @Column(name = "fecha_creado")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha;

    @OneToMany(mappedBy = "detalle", cascade = CascadeType.ALL)
    private List<Detalle> detalles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_comercial")
    private DocumentoComercial documentoComercial;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "proveedor")
    private Proveedor proveedor;

    public Presupuesto() { }

    public Presupuesto(List<CategoriaCriterio> categorias, List<Detalle> detalles, DocumentoComercial documentoComercial, Proveedor proveedor) {
        this.categorias = categorias;
        this.valor = detalles.stream().map(det-> det.getValor()*det.getCantidad()).reduce(0f, (subtotal, element) -> subtotal + element);
        this.detalles = detalles;
        this.documentoComercial = documentoComercial;
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor(){return this.proveedor;}
    public List<Criterio> getCriterios() { return categorias.stream().map(cat->cat.getCriterio()).collect(Collectors.toList()); }
    public List<CategoriaCriterio> getCategoria() { return categorias; }
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

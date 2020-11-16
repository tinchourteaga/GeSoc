package Dominio.Egreso.Core;

import Converters.LocalDateAttributeConverter;
import Converters.SeleccionProveedorAttributeConverter;
import Dominio.BandejaMensajes.Mensaje;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Ingreso;
import Dominio.Moneda.Valor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dom_egresos")
public class Egreso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int egreso;

    @Column(name = "validado")
    private boolean estaVerificada;

    @Column(name = "fecha")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "valor")
    private Valor valor;

    @OneToMany(mappedBy = "egreso", cascade = CascadeType.ALL)
    private List<Item> listaItems;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "metodo_pago")
    private MetodoDePago metodoDePago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_comercial")
    private DocumentoComercial documentoComercial;

    @Column(name = "criterio_seleccion_proveedor")
    @Convert(converter = SeleccionProveedorAttributeConverter.class)
    private CriterioSeleccionProveedor criterioSeleccionProveedor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dom_egresos_dom_criterios",
            joinColumns = { @JoinColumn(name = "egreso") },
            inverseJoinColumns = { @JoinColumn(name = "categoria") }
    )
    private List<CategoriaCriterio> categorias=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ingreso", referencedColumnName = "ingreso")
    private Ingreso ingreso;

    @ManyToOne
    @JoinColumn(name = "entidad", referencedColumnName = "entidad")
    private Entidad entidad;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "egreso_asociado")
    List<Mensaje> mensajes = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "presupuesto_pactado")
    private Presupuesto presupuestoPactado;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "egreso_presupuestado")
    private List<Presupuesto> presupuestosAConsiderar;

    public Egreso() { }

    public Egreso(LocalDate unaFecha, String pais, List<Item> items, MetodoDePago metodo, List<Presupuesto> presupuestos, DocumentoComercial unDocumento, CriterioSeleccionProveedor criterio){
       this.categorias=new ArrayList<>();
       this.fecha=unaFecha;
       this.valor= new Valor(pais,items.stream().map(det-> det.getValor()*det.getCantidad()).reduce(0f, (subtotal, element) -> subtotal + element));
       this.listaItems=items;
       this.metodoDePago=metodo;
       this.documentoComercial=unDocumento;
       this.presupuestosAConsiderar=presupuestos;
       this.setCriterio(criterio);
       this.estaVerificada=false;
    }

    public void setCriterio(CriterioSeleccionProveedor criterio){
        this.criterioSeleccionProveedor = criterio;
    }

    public void elegirPresupuesto(){
        presupuestoPactado=this.criterioSeleccionProveedor.seleccionarPresupuesto(this.presupuestosAConsiderar);
    }

    public CriterioSeleccionProveedor getCriterio(){
        return this.criterioSeleccionProveedor;
    }

    public List<Presupuesto> getPresupuestosAConsiderar(){return this.presupuestosAConsiderar;}

    public List<Criterio> getCriterioDeCategorizacion() {
        return categorias.stream().map(cat-> cat.getCriterio()).collect(Collectors.toList());
    }

    public LocalDate getFecha() { return fecha; }

    public Valor getValor() {return valor; }

    public List<Item> getListaItems() {return listaItems; }

    public MetodoDePago getMetodoDePago() { return metodoDePago; }

    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }

    public void validar(){
        ValidadorDeOperacion.validarDefault(this);
    }

    public List<CategoriaCriterio> getCategorias() {
        return this.categorias;
    }

    public boolean isEstaVerificada() {
        return estaVerificada;
    }

    public void setEstaVerificada(boolean estaVerificada) {
        this.estaVerificada = estaVerificada;
    }

    public void setEgreso(int egreso) {
        this.egreso = egreso;
    }
    public int getEgreso() {
        return egreso;
    }

    public Entidad getEntidad() {return entidad;}

    public void setEntidad(Entidad entidad) {this.entidad = entidad;}

    public Presupuesto getPresupuestoPactado() {
        return presupuestoPactado;
    }

    public void setPresupuestoPactado(Presupuesto presupuestoPactado) {
        this.presupuestoPactado = presupuestoPactado;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public Ingreso getIngreso(){
        return this.ingreso;
    }

    public void agregarItem(Item item){
        listaItems.add(item);
    }
}

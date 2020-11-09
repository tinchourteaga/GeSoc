package Dominio.Egreso.Core;

import Converters.LocalDateAttributeConverter;
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

@Entity
@Table(name = "dom_egresos")
public class Egreso {
    @Id
    @GeneratedValue
    private int egreso;

    // ESTO NO DEBERIA IR, PERO SI LO SACO ROMPE HASTA TU HERMANA TROLA Y ME DA PAJA
    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "proveedor")
    private Proveedor proveedorSeleccionado;
    //

    @Column(name = "validado")
    private boolean estaVerificada;

    @Column(name = "fecha")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha;

    @OneToOne
    @JoinColumn(name = "valor")
    private Valor valor;

    @OneToMany(mappedBy = "egreso", cascade = CascadeType.ALL)
    private List<Item> listaItems;

    @ManyToOne
    @JoinColumn(name = "metodo_pago", referencedColumnName = "metodo_pago")
    private MetodoDePago metodoDePago;

    @OneToOne
    @JoinColumn(name = "documento_comercial")
    private DocumentoComercial documentoComercial;

    @Transient
    private CriterioSeleccionProveedor criterioSeleccionProveedor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Criterio> criterios;

    @ManyToOne
    @JoinColumn(name = "ingreso", referencedColumnName = "ingreso")
    private Ingreso ingreso;

    @ManyToOne
    @JoinColumn(name = "entidad", referencedColumnName = "entidad")
    private Entidad entidad;

    @Transient
    private Presupuesto presupuestoPactado;

    @Transient
    private List<Presupuesto> presupuestosAConsiderar;

    public Egreso() { }

    public Egreso(LocalDate unaFecha, String pais, double importe, List<Item> items, MetodoDePago metodo, List<Proveedor> proveedores, DocumentoComercial unDocumento, CriterioSeleccionProveedor criterio){
       this.criterios=new ArrayList<>();
       this.fecha=unaFecha;
       this.valor= new Valor(pais,importe);
       this.listaItems=items;
       this.metodoDePago=metodo;
       this.documentoComercial=unDocumento;
       this.setCriterio(criterio);
       this.proveedorSeleccionado = criterio.seleccionarProveedor(proveedores);
       this.estaVerificada=false;
    }

    public void setCriterio(CriterioSeleccionProveedor criterio){
        this.criterioSeleccionProveedor = criterio;
    }

    public CriterioSeleccionProveedor getCriterio(){
        return this.criterioSeleccionProveedor;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public List<Criterio> getCriterioDeCategorizacion() {
        return criterios;
    }

    public LocalDate getFecha() { return fecha; }

    public Valor getValor() {return valor; }

    public List<Item> getListaItems() {return listaItems; }

    public MetodoDePago getMetodoDePago() { return metodoDePago; }

    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }

    public void asignarCriterioDeCategorizacion(Criterio criterioDeCategorizacion) {this.criterios.add(criterioDeCategorizacion); }

    public void validar(){
        ValidadorDeOperacion.validarDefault(this);
    }

    public List<CategoriaCriterio> getCategorias() {

        List<CategoriaCriterio> todasLasCategoriaCriterios =new ArrayList();
        criterios.forEach(criterio->criterio.getCategoriaCriterios().forEach(categoriaCriterio -> todasLasCategoriaCriterios.add(categoriaCriterio)));
        return todasLasCategoriaCriterios;
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

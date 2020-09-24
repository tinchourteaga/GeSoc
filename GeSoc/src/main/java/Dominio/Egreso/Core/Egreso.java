package Dominio.Egreso.Core;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Categoria;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
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

    // ver la relacion egreso - proveedor - presupuesto
    @Transient
    private Proveedor proveedorSeleccionado;

    @Column(name = "validado")
    private boolean estaVerificada;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @Transient
    private Valor valor;

    @Transient
    private List<Item> listaItems ;

    @Transient
    private MetodoDePago metodoDePago;

    @Transient
    private DocumentoComercial documentoComercial;

    @Transient
    private CriterioSeleccionProveedor criterioSeleccionProveedor;

    @Transient
    private List<Criterio> criterios;

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

    public List<Categoria> getCategorias() {

        List<Categoria> todasLasCategorias=new ArrayList();
        criterios.forEach(criterio->criterio.getCategorias().forEach(categoria->todasLasCategorias.add(categoria)));
        return todasLasCategorias;
    }

    public boolean isEstaVerificada() {
        return estaVerificada;
    }

    public void setEstaVerificada(boolean estaVerificada) {
        this.estaVerificada = estaVerificada;
    }
}

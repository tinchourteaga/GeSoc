package Dominio.Egreso.Core;


import Dominio.Egreso.Core.CriteriosDeCategorizacion.Categoria;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Egreso {

    private boolean estaVerificada;
    private Date fecha;
    private Float valor;
    private List<Item> listaItems ;
    private MetodoDePago metodoDePago;
    private Proveedor proveedorSeleccionado;
    private DocumentoComercial documentoComercial;
    private CriterioSeleccionProveedor criterioSeleccionProveedor;
    private List<Criterio> criterios;


    public Egreso(Date unaFecha, float importe, List<Item> items, MetodoDePago metodo, List<Proveedor> proveedores, DocumentoComercial unDocumento, CriterioSeleccionProveedor criterio){
       this.criterios=new ArrayList<>();
       this.fecha=unaFecha;
       this.valor=importe;
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
    public Date getFecha() { return fecha; }
    public Float getValor() {return valor; }
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

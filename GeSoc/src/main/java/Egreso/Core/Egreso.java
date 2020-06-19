package Egreso.Core;

import Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.ValidadorDeOperacion;
import Usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Egreso {

    LocalDate fecha;
    Float valor;
    List<Item> listaItems = new ArrayList<Item>();
    MetodoPago metodoPago;
    List<Proveedor> proveedores = new ArrayList<Proveedor>();
    Proveedor proveedorSeleccionado;
    Categoria unaCategoria;
    DocumentoComercial documentoComercial;
    CriterioSeleccionProveedor criterio;


    public Egreso(LocalDate unaFecha,float importe,List<Item> items,MetodoPago metodo,List<Proveedor> proveedores, DocumentoComercial unDocumento, CriterioSeleccionProveedor criterio){
       this.fecha=unaFecha;
       this.valor=importe;
       this.listaItems=items;
       this.metodoPago=metodo;
       this.proveedores=proveedores;
       this.documentoComercial=unDocumento;
       this.setCriterio(criterio);
       this.proveedorSeleccionado = criterio.seleccionarProveedor(this.proveedores);
        
    }

    public void setCriterio(CriterioSeleccionProveedor criterio){
        this.criterio = criterio;
    }

    public CriterioSeleccionProveedor getCriterio(){
        return this.criterio;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void realizar() {
        //esto no lo implemento porque todavia no sabemos que hace bien
    }

    //esto es de 2da entrega no lo implemento todavia
    public void revisar(Usuario usuario) {
    }

    public void validar() throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        ValidadorDeOperacion.validarDefault(this);
    }
}

package Egreso.Core;

import BandejaMensajes.Mensaje;
import Egreso.Core.CriteriosDeCategorizacion.Categoria;
import Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.ValidadorDeOperacion;
import Ingreso.Ingreso;
import Usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Egreso {

    Date fecha;
    Float valor;
    List<Item> listaItems = new ArrayList<Item>();
    MetodoDePago metodoDePago;
    List<Proveedor> proveedores = new ArrayList<Proveedor>();
    Proveedor proveedorSeleccionado;
    DocumentoComercial documentoComercial;
    CriterioSeleccionProveedor criterioSeleccionProveedor;
    Criterio criterioDeCategorizacion;
    Ingreso ingresoOpcional;
    List<Categoria> categorias=new ArrayList<>();//son varias categorias
    /* un ejemplo seria que tenes Alcance de proyecto Internacional
    y despues tamanio de alguna otra cosa*/

    public Ingreso getIngresoOpcional() {
        return ingresoOpcional;
    }

    public void asociarIngreso(Ingreso ingresoOpcional) {
        this.ingresoOpcional = ingresoOpcional;
    }

    public Egreso(Date unaFecha, float importe, List<Item> items, MetodoDePago metodo, List<Proveedor> proveedores, DocumentoComercial unDocumento, CriterioSeleccionProveedor criterio){
       this.fecha=unaFecha;
       this.valor=importe;
       this.listaItems=items;
       this.metodoDePago=metodo;
       this.proveedores=proveedores;
       this.documentoComercial=unDocumento;
       this.setCriterio(criterio);
       this.proveedorSeleccionado = criterio.seleccionarProveedor(this.proveedores);
        
    }

    public void setCriterio(CriterioSeleccionProveedor criterio){
        this.criterioSeleccionProveedor = criterio;
    }

    public CriterioSeleccionProveedor getCriterio(){
        return this.criterioSeleccionProveedor;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }




    public Criterio getCriterioDeCategorizacion() {
        return criterioDeCategorizacion;
    }

    public void asignarCriterioDeCategorizacion(Criterio criterioDeCategorizacion) {
        this.criterioDeCategorizacion = criterioDeCategorizacion;
    }

    public void obtenerCategoria() {
        criterioDeCategorizacion.aplicar(this);
    }

    public /*Mensaje*/ void validar() throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        //con roles compartidos
        //return ValidadorDeOperacion.validarDefault(this);

        //roles exclusivos
        ValidadorDeOperacion.validarDefault(this);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}

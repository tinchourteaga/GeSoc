package Egreso.Core;


import Egreso.Core.CriteriosDeCategorizacion.Categoria;
import Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.ValidadorDeOperacion;
import Ingreso.Ingreso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Egreso {

    private Date fecha;
    private Float valor;
    private List<Item> listaItems = new ArrayList<Item>();
    private MetodoDePago metodoDePago;
    private List<Proveedor> proveedores = new ArrayList<Proveedor>();
    private Proveedor proveedorSeleccionado;
    private DocumentoComercial documentoComercial;
    private CriterioSeleccionProveedor criterioSeleccionProveedor;
    private Ingreso ingresoOpcional;
    private List<Criterio> criterios=new ArrayList<>();//son varias categorias
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




    public List<Criterio> getCriterioDeCategorizacion() {
        return criterios;
    }

    public void asignarCriterioDeCategorizacion(Criterio criterioDeCategorizacion) {
        this.criterios.add(criterioDeCategorizacion);
    }

    public /*Mensaje*/ void validar() throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        //con roles compartidos
        //return ValidadorDeOperacion.validarDefault(this);

        //roles exclusivos
        ValidadorDeOperacion.validarDefault(this);
    }

    public List<Categoria> getCategorias() {

        List<Categoria> todasLasCategorias=new ArrayList();
        criterios.forEach(criterio->criterio.getCategorias().forEach(categoria->todasLasCategorias.add(categoria)));
        return todasLasCategorias;
    }
}

package Dominio.Egreso.Core;

import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Entidad.Direccion;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {

    private String razonSocial;
    private String nombreApellido;
    private String cuitODni;
    private Direccion direccion;
    private List<Presupuesto> presupuestos;

    public Proveedor(String rs,String nombreYApellido,String cuitODni,Direccion unaDireccion) {
        this.razonSocial=rs;
        this.nombreApellido=nombreYApellido;
        this.cuitODni=cuitODni;
        this.direccion=unaDireccion;
        this.presupuestos= new ArrayList<Presupuesto>();
    }

    public String getRazonSocial() {
        return razonSocial;
    }
    public String getNombreApellido() {
        return nombreApellido;
    }
    public String getCuitODni() {
        return cuitODni;
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }
    public Presupuesto getPresupuestoCriterio(CriterioSeleccionProveedor criterio){ return criterio.seleccionarPresupuesto(presupuestos); }
    public void setNombreApellido(String nombreApellido) { this.nombreApellido = nombreApellido; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
}

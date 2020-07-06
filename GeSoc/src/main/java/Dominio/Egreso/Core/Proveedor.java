package Dominio.Egreso.Core;

import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;

import java.util.List;

public class Proveedor {

    private String razonSocial;
    private String nombreApellido;
    private String cuitODni;
    private String direccion;
    private List<Presupuesto> presupuestos;

    public Proveedor(String rs,String nombreYApellido,String CUITODNI,String unaDireccion, List<Presupuesto> presupuesto) {
        this.razonSocial=rs;
        this.nombreApellido=nombreYApellido;
        this.cuitODni=CUITODNI;
        this.direccion=unaDireccion;
        this.presupuestos=presupuesto;
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

    public String getDireccion() {
        return direccion;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public Presupuesto getPresupuestoCriterio(CriterioSeleccionProveedor criterio){
        return criterio.seleccionarPresupuesto(presupuestos);
    }
}

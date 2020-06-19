package Operacion.Core;

public class Proveedor {

    String razonSocial;
    String nombreApellido;
    String cuitODni;
    String direccion;
    Presupuesto presupuesto;

    public Proveedor(String rs,String nombreYApellido,String CUITODNI,String unaDireccion, Presupuesto presupuesto) {
        this.razonSocial=rs;
        this.nombreApellido=nombreYApellido;
        this.cuitODni=CUITODNI;
        this.direccion=unaDireccion;
        this.presupuesto=presupuesto;
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

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }
}

package Egreso.Core;

public class Proveedor {

    private String razonSocial;
    private String nombreApellido;
    private String cuitODni;
    private String direccion;
    private Presupuesto presupuesto;

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

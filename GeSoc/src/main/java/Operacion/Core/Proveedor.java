package Operacion.Core;

public class Proveedor {

    String razonSocial;
    String nombreApellido;
    String cuitODni;
    String direccion;

    public Proveedor(String rs,String nombreYApellido,String CUITODNI,String unaDireccion) {
        this.razonSocial=rs;
        this.nombreApellido=nombreYApellido;
        this.cuitODni=CUITODNI;
        this.direccion=unaDireccion;
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



}

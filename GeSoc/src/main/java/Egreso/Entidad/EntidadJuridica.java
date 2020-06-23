package Egreso.Entidad;

import Egreso.Core.Egreso;
import Ingreso.Ingreso;

import java.util.ArrayList;
import java.util.List;

public class EntidadJuridica extends Entidad{
    protected String razonSocial;
    protected String cuit;
    protected String codigoPostal;
    protected String codigoDeInscripcion;
    protected String tipo;
    protected Sector actividad;
    protected Integer cantidadPersonal;
    protected Float promedioVentasAnuales;
    protected List<Egreso> egresos=new ArrayList<>();
    protected List<Ingreso> ingresos=new ArrayList<>();

    public EntidadJuridica(String nombreEntidad, String descripcionEntidad, List<Egreso> operacionesEntidad,
                           String rs,String cuitEntidad,String cp,String ci,
                           String tipo,Sector actividad,Integer personal,Float promedio) {
        super(nombreEntidad, descripcionEntidad, operacionesEntidad);

        this.razonSocial = rs;
        this.cuit = cuitEntidad;
        this.codigoPostal = cp;
        this.codigoDeInscripcion = ci;
        this.tipo = tipo;
        this.actividad = actividad;
        this.cantidadPersonal = personal;
        this.promedioVentasAnuales = promedio;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoDeInscripcion() {
        return codigoDeInscripcion;
    }

    public void setCodigoDeInscripcion(String codigoDeInscripcion) {
        this.codigoDeInscripcion = codigoDeInscripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sector getActividad() {
        return actividad;
    }

    public void setActividad(Sector actividad) {
        this.actividad = actividad;
    }

    public Integer getCantidadPersonal() {
        return cantidadPersonal;
    }

    public void setCantidadPersonal(Integer cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }

    public Float getPromedioVentasAnuales() {
        return promedioVentasAnuales;
    }

    public void setPromedioVentasAnuales(Float promedioVentasAnuales) {
        this.promedioVentasAnuales = promedioVentasAnuales;
    }
    public void agregarEgreso(Egreso unEgreso){
        egresos.add(unEgreso);
    }

    public void agregarIngreso(Ingreso unIngreso){
        ingresos.add(unIngreso);
    }

    public void removerEgreso(Egreso unEgreso){egresos.remove(unEgreso);}

    public void removerIngreso(Ingreso unIngreso){ingresos.remove(unIngreso);}
}

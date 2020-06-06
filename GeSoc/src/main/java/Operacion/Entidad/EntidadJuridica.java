package Operacion.Entidad;

import Operacion.Core.Operacion;

import java.util.List;

public class EntidadJuridica extends Entidad{
    String razonSocial;
    String cuit;
    String codigoPostal;
    String codigoDeInscripcion;
    String tipo;
    Sector actividad;
    Integer cantidadPersonal;
    Float promedioVentasAnuales;

    public EntidadJuridica(String nombreEntidad, String descripcionEntidad, List<Operacion> operacionesEntidad,
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



}

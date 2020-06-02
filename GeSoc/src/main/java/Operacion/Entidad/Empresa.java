package Operacion.Entidad;

import Operacion.Operacion;

import java.util.List;

public class Empresa extends EntidadJuridica {

    Categoria categoria;

    public Empresa(String nombreEntidad, String descripcionEntidad, List<Operacion> operacionesEntidad,
                   String rs,String cuitEntidad,String cp,String ci,String tipo,String actividad,Integer personal,Float promedio){
        super(nombreEntidad,descripcionEntidad,operacionesEntidad,rs,cuitEntidad,cp,ci,tipo,actividad,personal,promedio);

        this.determinarCategoria();
    }

    public void determinarCategoria(){
        //implementar segun el link del AFIP
    }
}

package Operacion.Entidad;

import Operacion.Core.Operacion;
import Operacion.Entidad.Categorias.Categoria;

import java.util.List;

public class Empresa extends EntidadJuridica {

    Categoria categoria;

    public Empresa(String nombreEntidad, String descripcionEntidad, List<Operacion> operacionesEntidad,
                   String rs,String cuitEntidad,String cp,String ci,String tipo,
                   TipoActividad actividad,Integer personal,Float promedio){
        super(nombreEntidad,descripcionEntidad,operacionesEntidad,
                rs,cuitEntidad,cp,ci,tipo,actividad,personal,promedio);
    }


}

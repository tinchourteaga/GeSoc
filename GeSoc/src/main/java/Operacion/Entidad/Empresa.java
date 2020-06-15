package Operacion.Entidad;

import Operacion.Core.Operacion;
import Operacion.Entidad.Categorias.Categoria;
import Operacion.Entidad.Categorias.Categorizador;

import java.util.List;

public class Empresa extends EntidadJuridica {

    public Categoria categoria;

    public Empresa(String nombreEntidad, String descripcionEntidad, List<Operacion> operacionesEntidad,
                   String rs,String cuitEntidad,String cp,String ci,String tipo,
                   Sector actividad,Integer personal,Float promedio){
        super(nombreEntidad,descripcionEntidad,operacionesEntidad,
                rs,cuitEntidad,cp,ci,tipo,actividad,personal,promedio);
       Categorizador.determinarCategoria(this);
    }

}

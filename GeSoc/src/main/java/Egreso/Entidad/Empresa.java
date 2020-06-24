package Egreso.Entidad;

import Egreso.Core.Egreso;
import Egreso.Entidad.Categorias.Categoria;
import Egreso.Entidad.Categorias.Categorizador;

import java.util.List;

public class Empresa extends EntidadJuridica {

    private Categoria categoria;

    public Empresa(String nombreEntidad, String descripcionEntidad, List<Egreso> operacionesEntidad,
                   String rs,String cuitEntidad,String cp,String ci,String tipo,
                   Sector actividad,Integer personal,Float promedio){
        super(nombreEntidad,descripcionEntidad,operacionesEntidad,
                rs,cuitEntidad,cp,ci,tipo,actividad,personal,promedio);
       Categorizador.determinarCategoria(this);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

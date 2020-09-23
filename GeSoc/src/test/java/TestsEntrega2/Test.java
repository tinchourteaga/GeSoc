package TestsEntrega2;

import Dominio.Egreso.Core.Egreso;
import Dominio.Entidad.Categorias.*;
import Dominio.Entidad.Empresa;
import Dominio.Entidad.EntidadJuridica;
import Dominio.Entidad.Sector;
import Dominio.Entidad.TipoEntidadJuridica;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    String nombre = null;
    String descripcion = null;
    String rs = null;
    String cuit = null;
    String cp = null;
    String ci = null;
    String tipo = null;
    List<Egreso> operaciones = null;
    Empresa empresa = new Empresa();

    @Before
    public void init(){
    }

    @org.junit.Test
    public void testCategoriaMicroAgro(){

        Sector actividad = new Sector(new ArrayList<>(),"AGROPECUARIO"," ");

        Categoria categoria1 = new Categoria(TipoCategoria.MICRO,2,1000f);
        Categoria categoria2 = new Categoria(TipoCategoria.MEDIANA_TRAMO_1,7,13000f);
        Categoria categoria3 = new Categoria(TipoCategoria.MEDIANA_TRAMO_2,15,25000f);

        actividad.agregarCategoria(categoria1);
        actividad.agregarCategoria(categoria2);
        actividad.agregarCategoria(categoria3);

        Integer personal = 3;
        Float promedio = 12000000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        empresa.setActividad(actividad);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        empresa.determinarCategoria();

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }

}

package TestsEntrega2;

import Operacion.Core.Operacion;
import Operacion.Entidad.Categorias.*;
import Operacion.Entidad.Empresa;
import Operacion.Entidad.Sector;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class Test {

    @org.junit.Test
    public void testCategoriaMicro(){
        String nombre = null;
        String descripcion = null;
        List<Operacion> operaciones = null;
        String rs = null;
        String cuit = null;
        String cp = null;
        String ci = null;
        String tipo = null;
        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 3;
        Float promedio = 12000000f;

        DAOMemoriaCategoria daomem = new DAOMemoriaCategoria();

        Categorizador.setCategorias(daomem.obtenerMapaDeCategorias());

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                                            actividad, personal, promedio);


        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }

    public void testCategoriaPequenia(){

    }

    public void testCategoriaMedianaTramo1(){

    }

    public void testCategoriaMedianaTramo2(){

    }
}

package TestsEntrega2;

import Operacion.Core.Operacion;
import Operacion.Entidad.Categorias.*;
import Operacion.Entidad.Empresa;
import Operacion.Entidad.Sector;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class Test {
    String nombre = null;
    String descripcion = null;
    String rs = null;
    String cuit = null;
    String cp = null;
    String ci = null;
    String tipo = null;
    List<Operacion> operaciones = null;

    @Before
    public void init(){
        DAOMemoriaCategoria daoMemCat = new DAOMemoriaCategoria();
        Categorizador.setCategorias(daoMemCat.obtenerMapaDeCategorias());
    }


    @org.junit.Test
    public void testCategoriaMicroAgro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 3;
        Float promedio = 12000000f;


        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                                            actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaAgro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 9;
        Float promedio = 48420000f;


        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Agro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 35;
        Float promedio = 345410000f;


        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Agro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 120;
        Float promedio = 547850000f;


        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresaMicro.categoria.getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroComerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 6;
        Float promedio = 29730000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaComerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 20;
        Float promedio = 178800000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Comerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 110;
        Float promedio = 1502740000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Comerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 200;
        Float promedio = 2146800000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresaMicro.categoria.getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroConst(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 10;
        Float promedio = 15220000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaConst(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 30;
        Float promedio = 90300000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Const(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 160;
        Float promedio = 503850000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Const(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 515;
        Float promedio = 755740000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresaMicro.categoria.getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroIyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 13;
        Float promedio = 26510000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaIyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 50;
        Float promedio = 190400000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1IyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 110;
        Float promedio = 1190320000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2IyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 400;
        Float promedio = 1739590000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresaMicro.categoria.getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroServ(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 5;
        Float promedio = 8100000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaServ(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 25;
        Float promedio = 50930000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Serv(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 100;
        Float promedio = 425110000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresaMicro.categoria.getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Serv(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 520;
        Float promedio = 607200000f;

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresaMicro.categoria.getNombre());
    }

    /////////////////////////////////////////////////////////////

    //El test refleja a que categoria pertenece una empresa que tiene valores que corresponden a 2 categorias distintas
    @org.junit.Test
    public void testCategoriaValoresDiferentes(){
        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 4; //Por personal deberia ser Micro
        Float promedio = 547890000f; //Por ventas deberia ser MedTramo2

        Empresa empresaMicro = new Empresa(nombre, descripcion, operaciones, rs, cuit, cp, ci, tipo,
                actividad, personal, promedio);

        Assert.assertEquals(TipoCategoria.MICRO, empresaMicro.categoria.getNombre());
    }
}

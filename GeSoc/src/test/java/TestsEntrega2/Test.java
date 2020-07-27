package TestsEntrega2;

import Dominio.Egreso.Core.Egreso;
import Dominio.Entidad.Categorias.*;
import Dominio.Entidad.Empresa;
import Dominio.Entidad.EntidadJuridica;
import Dominio.Entidad.Sector;
import Dominio.Entidad.TipoEntidadJuridica;
import org.junit.*;

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
        DAOMemoriaCategoria daoMemCat = new DAOMemoriaCategoria();
        Categorizador.setCategorias(daoMemCat.obtenerMapaDeCategorias());
    }


    @org.junit.Test
    public void testCategoriaMicroAgro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 3;
        Float promedio = 12000000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MICRO, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaAgro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 9;
        Float promedio = 48420000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Agro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 35;
        Float promedio = 345410000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Agro(){

        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 120;
        Float promedio = 547850000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroComerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 6;
        Float promedio = 29730000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MICRO, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaComerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 20;
        Float promedio = 178800000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Comerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 110;
        Float promedio = 1502740000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Comerc(){
        Sector actividad = Sector.COMERCIO;
        Integer personal = 200;
        Float promedio = 2146800000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroConst(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 10;
        Float promedio = 15220000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MICRO, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaConst(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 30;
        Float promedio = 90300000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Const(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 160;
        Float promedio = 503850000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Const(){
        Sector actividad = Sector.CONSTRUCCION;
        Integer personal = 515;
        Float promedio = 755740000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroIyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 13;
        Float promedio = 26510000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MICRO, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaIyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 50;
        Float promedio = 190400000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1IyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 110;
        Float promedio = 1190320000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2IyM(){
        Sector actividad = Sector.INDUSTRIA_Y_MINERIA;
        Integer personal = 400;
        Float promedio = 1739590000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }

    /////////////////////////////////////////////////////////////

    @org.junit.Test
    public void testCategoriaMicroServ(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 5;
        Float promedio = 8100000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MICRO, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaPequeniaServ(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 25;
        Float promedio = 50930000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.PEQUENIA, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo1Serv(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 100;
        Float promedio = 425110000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_1, empresa.getCategoria().getNombre());
    }

    @org.junit.Test
    public void testCategoriaMedianaTramo2Serv(){
        Sector actividad = Sector.SERVICIOS;
        Integer personal = 520;
        Float promedio = 607200000f;

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }

    /////////////////////////////////////////////////////////////

    //El test refleja a que categoria pertenece una empresa que tiene valores que corresponden a 2 categorias distintas
    @org.junit.Test
    public void testCategoriaValoresDiferentes(){
        Sector actividad = Sector.AGROPECUARIO;
        Integer personal = 4; //Por personal deberia ser Micro
        Float promedio = 547890000f; //Por ventas deberia ser MedTramo2

        EntidadJuridica entidadJuridica = new EntidadJuridica(nombre,descripcion, empresa);

        entidadJuridica.getTipoEntidadJuridica().setActividad(actividad);
        entidadJuridica.getTipoEntidadJuridica().setCantidadPersonal(personal);
        entidadJuridica.getTipoEntidadJuridica().setPromedioVentasAnuales(promedio);

        Categorizador.determinarCategoria(empresa);

        //El resultado esperado es uqe sea MEDIANA TRAMO 2 porque de dos categorias siempre me quedo con la mayor
        Assert.assertEquals(TipoCategoria.MEDIANA_TRAMO_2, empresa.getCategoria().getNombre());
    }
}

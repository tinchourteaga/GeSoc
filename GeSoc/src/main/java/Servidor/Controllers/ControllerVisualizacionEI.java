package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Entidad.Categorias.Categoria;
import Dominio.Entidad.Categorias.TipoCategoria;
import Dominio.Entidad.Entidad;
import Dominio.Entidad.EntidadJuridica;
import Dominio.Entidad.OrganizacionSocial;
import Dominio.Entidad.Sector;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerVisualizacionEI {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        //Estas tres listas las tenemos que traer de la BD
        List<Entidad> entidades = new ArrayList<>();
        List<Criterio> criterios = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();

        Entidad entidadPrueba1 = new EntidadJuridica("entidadPrueba1", "JorgeCampeon",new OrganizacionSocial());
        entidadPrueba1.setEntidad(57);
        entidades.add(entidadPrueba1);

        Entidad entidadPrueba2 = new EntidadJuridica("entidadPrueba2", "JuanCampeon",new OrganizacionSocial());
        entidadPrueba2.setEntidad(37);
        entidades.add(entidadPrueba2);

        Criterio criterioPrueba = new Criterio(new ArrayList<>(),"criterioPrueba", "TinchoCampeon");
        criterioPrueba.setCriterio(13);
        criterios.add(criterioPrueba);

        Categoria categoriaPrueba = new Categoria(new Sector(new ArrayList<>(),"LALA","LELE"), TipoCategoria.MEDIANA_TRAMO_1,200,20f);
        categoriaPrueba.setCategoria(9);
        categorias.add(categoriaPrueba);

        datos.put("entidades",entidades);
        datos.put("criterios",criterios);
        datos.put("categorias",categorias);

        String id = request.queryParams("entidad");

        System.out.println(id);

        ModelAndView vista = new ModelAndView(datos, "ver_ingresos_y_egresos.html");

        return vista;
    }

}

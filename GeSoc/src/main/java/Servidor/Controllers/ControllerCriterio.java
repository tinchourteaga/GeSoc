package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Entidad.Entidad;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import org.apache.commons.lang3.math.NumberUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerCriterio {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        Usuario miUsuario=ControllerSesion.obtenerUsuariodeSesion(request);

        List<Entidad>entidades=miUsuario.getEntidades();
        Set<Entidad>entidadesSet=new HashSet<>();
        entidadesSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadesSet);
        List<Criterio> criterios = entidades.stream().map(entidad->entidad.getCriterios()).flatMap(List::stream).collect(Collectors.toList());
        datos.put("entidades",entidades);
        datos.put("criterios", criterios);

        ModelAndView vista = new ModelAndView(datos, "crear_criterio.html");
        return vista;
    }

    public static Object crearCriterio(Request request, Response response) {

        String nombreCriterio = request.queryParams("nombreCriterio");
        String descripcion = request.queryParams("descripcion");
        String entidad = request.queryParams("entidad");
        String idCriterioPadre=request.queryParams("criterioPadre");

        List categoriasAsociadas = new ArrayList();

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        Criterio criterio = new Criterio(categoriasAsociadas, nombreCriterio, descripcion);
        List<Entidad> entidades = miUsuario.getEntidades();
        if (entidad!=null &&  NumberUtils.isNumber(entidad)) {
            entidades.stream().filter(entidad1 -> entidad1.getEntidad() == Integer.valueOf(entidad)).findFirst().ifPresent(entidad1 -> criterio.setEntidad(entidad1));
        }

        Repositorio repoCriterios= new Repositorio(new DAOBBDD<Criterio>(Criterio.class));
        List<Criterio> criteriosPadres = repoCriterios.getTodosLosElementos();
        if(!idCriterioPadre.equals("") && NumberUtils.isNumber(entidad)) {
            criteriosPadres.stream().filter(crit -> crit.getCriterio() == Integer.valueOf(idCriterioPadre).intValue()).findFirst().ifPresent(nuevoPadre ->
                    {
                        nuevoPadre.agregarHijos(criterio);
                        criterio.setCriterio_padre(nuevoPadre);
                        //persistirCriterio(nuevoPadre);//esto creo que no va
                    }
            );
        }
        persistirCriterio(criterio);
        response.redirect("crear_criterio");

        return null;
    }

    public static void persistirCriterio(Criterio criterio){

        DAO DAOCriterio = new DAOBBDD<Criterio>(Criterio.class); //dao generico de BBDD
        Repositorio repoCriterio = new Repositorio<Criterio>(DAOCriterio); //repositorio que tambien usa generics

        if(criterio.getCriterio()==0) {
            repoCriterio.agregar(criterio);
        }else{
            repoCriterio.modificar(null,criterio);
        }
    }
}

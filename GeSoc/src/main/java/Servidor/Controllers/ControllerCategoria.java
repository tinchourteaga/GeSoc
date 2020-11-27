package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerCategoria {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        DAO DAOCategorias = new DAOBBDD<CategoriaCriterio>(CategoriaCriterio.class); //dao generico de BBDD
        Repositorio repoCategorias = new Repositorio<Criterio>(DAOCategorias); //repositorio que tambien usa generics
        List<CategoriaCriterio> categorias =miUsuario.getEgresosAREvisar().stream().map(e->e.getCategorias()).flatMap(List::stream).collect(Collectors.toList());
        Set<CategoriaCriterio> categoriasSet=new HashSet<>();
        categoriasSet.addAll(categorias);
        categorias.clear();
        categorias.addAll(categoriasSet);

        List<Criterio> criteriosLista= miUsuario.getEgresosAREvisar().stream().map(e->e.getCategorias()).flatMap(List::stream).collect(Collectors.toList()).stream().map(c->c.getCriterio()).collect(Collectors.toList());
        Set<Criterio> criteriosSet=new HashSet<>();
        criteriosSet.addAll(criteriosLista);
        criteriosLista.clear();
        criteriosLista.addAll(criteriosSet);
        datos.put("criterios",criteriosLista);
        datos.put("categorias", categorias);

        ModelAndView vista = new ModelAndView(datos, "crear_categoria.html");

        return vista;
    }

    public static Object crearCategoria(Request request, Response response) {

        String nombreCategoria = request.queryParams("nombreCategoria");
        String descripcion = request.queryParams("descripcion");
        String criterioAsociado = request.queryParams("criterioAsociado");

        CategoriaCriterio categoriaCriterio = new CategoriaCriterio(descripcion,nombreCategoria);

        persistirCategoria(categoriaCriterio, criterioAsociado);

        response.redirect("crear_categoria");

        return null;
    }

    public static void persistirCategoria(CategoriaCriterio categoriaCriterio, String criterioAsociado){

        DAO DAOCriterio = new DAOBBDD<Criterio>(Criterio.class); //dao generico de BBDD
        Repositorio repoCriterio = new Repositorio<Criterio>(DAOCriterio); //repositorio que tambien usa generics

        List<Criterio> criterios = repoCriterio.getTodosLosElementos();

        List<Criterio> criteriosPosibles=criterios.stream().filter(c-> c.getCriterio()==Integer.valueOf(criterioAsociado).intValue()).collect(Collectors.toList());

            criteriosPosibles.stream().findFirst().ifPresent(criterioPresente->{
                criterioPresente.agregarCategoria(categoriaCriterio);
                repoCriterio.modificar(null, criterioPresente);
            });
        DAO DAOCategoria = new DAOBBDD<CategoriaCriterio>(CategoriaCriterio.class); //dao generico de BBDD
        Repositorio repoCategoria = new Repositorio<CategoriaCriterio>(DAOCategoria); //repositorio que tambien usa generics

        if(!repoCriterio.existe(categoriaCriterio)) {
            repoCriterio.agregar(categoriaCriterio);
        }else{
            repoCriterio.modificar(null,categoriaCriterio);
        }

        }
    }

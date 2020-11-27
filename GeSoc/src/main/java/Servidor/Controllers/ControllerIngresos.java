package Servidor.Controllers;

import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Ingreso;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerIngresos {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        List<Entidad> entidades= miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad()).collect(Collectors.toList());

        Set<Entidad> setEntidades= new HashSet<Entidad>();
        setEntidades.addAll(entidades);
        entidades.clear();
        entidades.addAll(setEntidades);

        Entidad entidad = miUsuario.getEntidades().get(0);

        Repositorio repoIngreso = new Repositorio(new DAOBBDD<Ingreso>(Ingreso.class));
        List<Ingreso> ingresosPosibles = repoIngreso.getTodosLosElementos();
        List<Ingreso> ingresosTabla = ingresosPosibles.stream().filter(e -> e.getEntidad().equals(entidad)).collect(Collectors.toList());

        datos.put("entidades",entidades);
        datos.put("egreso",ingresosTabla);

        ModelAndView vista = new ModelAndView(datos, "cargar_ingreso.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaDetalleIngreso(Request request, Response response) {

        Map<String, Object> datos= new HashMap<>();

        Optional<String> ingreso = Optional.ofNullable(request.queryParams("ingreso"));
        Optional<String> categoriaFiltro = Optional.ofNullable(request.queryParams("categoriaFiltro"));
        Optional<String> entidad = Optional.ofNullable(request.queryParams("entidad"));

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        List<Entidad> entidades= miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad()).collect(Collectors.toList());
        Set<Entidad> entidadSet= new HashSet<>();
        entidadSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadSet);

        datos.put("entidades", entidades);

        final List<Ingreso>[] ingresosQueManejo = new List[]{entidades.stream().map(e -> e.getIngresos()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList())};

        categoriaFiltro.filter(cat-> !cat.equals("seleccione")).ifPresent(cat->{
            ingresosQueManejo[0] = ingresosQueManejo[0].stream().filter(ingresoDeLista->ingresoDeLista.getCategoriasAsociadas().stream().anyMatch(cate-> cate.getCategoria()==Integer.valueOf(cat).intValue())).collect(Collectors.toList());
            entidades.stream().map(e->e.getCriterios()).flatMap(List::stream).collect(Collectors.toList()).stream().map(cri-> cri.getCategoriaCriterios()).flatMap(List::stream).filter(categoriaposta->categoriaposta.getCategoria()==Integer.valueOf(cat).intValue()).findFirst().ifPresent(categoriaCriterio -> datos.put("categoriaFiltro",categoriaCriterio));
        });
        entidad.filter(ent-> !ent.equals("seleccione")).ifPresent(ent->{
            ingresosQueManejo[0] = ingresosQueManejo[0].stream().filter(ingresoDeLista->ingresoDeLista.getEntidad().getEntidad()==Integer.valueOf(ent).intValue()).collect(Collectors.toList());
            ingresosQueManejo[0].stream().findFirst().ifPresent(ingreso1 -> datos.put("entidadFiltro",ingreso1.getEntidad()));
        });

        datos.put("ingresos", ingresosQueManejo[0]);

        ingreso.filter(ingresito->!ingresito.equals("seleccione")).ifPresent(ing->{
                List<Ingreso> posiblesIngresos= ingresosQueManejo[0].stream().filter(in->in.getIngreso()==Integer.valueOf(ing)).collect(Collectors.toList());
            posiblesIngresos.stream().findFirst().ifPresent(ingresoPresente->datos.put("ingreso", ingresoPresente));
        });

        datos.put("categorias", entidades.stream().map(e->e.getCriterios()).flatMap(List::stream).collect(Collectors.toList()).stream().map(cri-> cri.getCategoriaCriterios()).flatMap(List::stream).collect(Collectors.toList()));
        //datos.put("egreso",egresosARevisar);
        return new ModelAndView(datos, "detalle_ingreso.html");
    }

    public static Object cargarIngreso(Request request, Response response) {

        Usuario miUsuario=ControllerSesion.obtenerUsuariodeSesion(request);
        String entidad = request.queryParams("entidad"); //No lo tengo en mi constructor -> es necesario?
        String fecha = request.queryParams("fecha");
        String moneda = request.queryParams("moneda");
        String importe = request.queryParams("importe");
        String descripcion = request.queryParams("descripcion");


        List egresosAsociados = new ArrayList();
        Ingreso ingreso = new Ingreso(moneda, Double.parseDouble(importe), LocalDate.parse(fecha),LocalDate.now(), descripcion, egresosAsociados);
        miUsuario.getEntidades().stream().filter(entidad1 -> entidad1.getEntidad() == Integer.valueOf(entidad).intValue()).findFirst().ifPresent(entidadCorrecta ->{
            ingreso.setEntidad(entidadCorrecta);
            persistirIngreso(ingreso);
        });
        response.redirect("cargar_ingreso");

        return null;
    }

    public static void persistirIngreso(Ingreso ingreso){

        DAO DAOIngreso = new DAOBBDD<Ingreso>(Ingreso.class); //dao generico de BBDD
        Repositorio repoIngreso = new Repositorio<Ingreso>(DAOIngreso); //repositorio que tambien usa generics

        if(!repoIngreso.existe(ingreso))
            repoIngreso.agregar(ingreso);

    }
}

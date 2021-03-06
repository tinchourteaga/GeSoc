package Servidor.Controllers;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Egreso.Core.Egreso;
import Dominio.Usuario.Usuario;
import Persistencia.QueriesUtiles;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerMensajes {


    public static ModelAndView visualizarPantallaMensajes(Request request, Response response) {

        String nombreUsuario = request.session().attribute("nombreUsuario");
        Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);

        if (usuario != null) {

            if (QueriesUtiles.obtenerEgresosDe(usuario.getNickName()).isEmpty()) {
                return visualizarPantallaMensajesNoRevisor(request, response);
            } else {
                return visualizarPantallaMensajesRevisor(request, response, usuario);
            }
        }
        return new ModelAndView(new HashMap<String, Object>(), "pantalla_principal.html");
    }

    public static ModelAndView visualizarPantallaMensajesNoRevisor(Request request, Response response) {

        Map<String, Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "mensajes_no_revisor.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaMensajesRevisor(Request request, Response response, Usuario usuario) {

        Usuario user = ControllerSesion.obtenerUsuariodeSesion(request);

        //con el usuario vas sacando datos
        String fecha = request.queryParams("fechaFiltrado");
        String egreso = request.queryParams("egreso");
        List<Mensaje> todosLosMsj = QueriesUtiles.obtenerTodosLosMensajes(user);

        if (egreso != null && fecha != null) {
            todosLosMsj = todosLosMsj.stream().filter(msj -> msj.getFechaCreado().isBefore(LocalDate.parse(fecha))&& msj.getEgreso().getEgreso()==Integer.valueOf(egreso).intValue()).collect(Collectors.toList());
        } else {

            if (fecha != null) {
                todosLosMsj = usuario.getBandejaDeMensajes().filtrarPorFecha(LocalDate.parse(fecha));
            } else {
                if (egreso != null) {
                    todosLosMsj = usuario.getBandejaDeMensajes().getMensajes().stream().filter(msj -> msj.getEgreso().getEgreso() == (Integer.valueOf(egreso))).collect(Collectors.toList());

                } else {
                    todosLosMsj = usuario.getBandejaDeMensajes().getMensajes();
                }
            }
            if (request.queryParams("filtrarPorLeidos")!=null && request.queryParams("filtrarPorLeidos").equals("true")) {
                todosLosMsj = todosLosMsj.stream().filter(msj -> msj.getLeido()).collect(Collectors.toList());
            }
        }
        List<Egreso> egresos=new ArrayList();
        if(todosLosMsj!=null && todosLosMsj.isEmpty()){
            egresos= todosLosMsj.stream().map(ms->ms.getEgreso()).collect(Collectors.toList());
        }
            Map<String, Object> datos = new HashMap<>();
            datos.put("mensajes", todosLosMsj);
            datos.put("egreso", egresos);
            ModelAndView vista = new ModelAndView(datos, "mensajes_revisor.html");

            return vista;

    }
}

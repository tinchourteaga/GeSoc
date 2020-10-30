package Servidor.Controllers;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.Administrador;
import Dominio.Rol.Estandar;
import Dominio.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerMensajes {


    public static ModelAndView visualizarPantallaMensajes(Request request, Response response){

        String nombreUsuario= request.session().attribute("nombreUsuario");

        //Usuario usuario= RepositorioUsuario.getInstance().buscarPorUsuario(nombreUsuario);//lo obtenes con el nombre de arriba

        Usuario usuario = null;
        try {
            usuario = new Usuario(new Administrador(), "Pedro", "Pratgay", "@guanteDDS2020","1561684586", "3AMatr@gmail.com");
        } catch (ExcepcionCaracterEspecial excepcionCaracterEspecial) {
            excepcionCaracterEspecial.printStackTrace();
        } catch (ExcepcionContraseniaComun excepcionContraseniaComun) {
            excepcionContraseniaComun.printStackTrace();
        } catch (ExcepcionNumero excepcionNumero) {
            excepcionNumero.printStackTrace();
        } catch (ExcepcionLongitud excepcionLongitud) {
            excepcionLongitud.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Estandar.class.equals(usuario.getRol().getClass())) {
            return visualizarPantallaMensajesNoRevisor(request,response);
        }else{
            return visualizarPantallaMensajesRevisor(request,response,usuario);
        }

    }

    public static ModelAndView visualizarPantallaMensajesNoRevisor(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "mensajes_no_revisor.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaMensajesRevisor(Request request, Response response,Usuario usuario){

        //con el usuario vas sacando datos
        String fecha=request.queryParams("fechaFiltrado");
        String egreso=request.queryParams("egreso");
        List<Mensaje> todosLosMsj;
        if(egreso!=null){

        //    todosLosMsj=usuario.getBandejaDeMensajes().getMensajes().stream().filter(msj->msj.);

        }

        if(fecha!=null){
            todosLosMsj=usuario.getBandejaDeMensajes().filtrarPorFecha(LocalDate.parse(fecha));
        }else {
            todosLosMsj = usuario.getBandejaDeMensajes().getMensajes();
        }
        if(request.queryParams("filtrarPorLeidos").equals("true")){

            todosLosMsj=todosLosMsj.stream().filter(msj->msj.getLeido()).collect(Collectors.toList());
        }

        Map<String,Object> datos = new HashMap<>();
        datos.put("mensajes",todosLosMsj);
        ModelAndView vista = new ModelAndView(datos, "mensajes_revisor.html");

        return vista;
    }
}

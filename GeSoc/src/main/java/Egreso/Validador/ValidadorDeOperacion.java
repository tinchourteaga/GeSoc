package Egreso.Validador;

import BandejaMensajes.Mensaje;
import Egreso.Core.Egreso;
import Egreso.Validador.DAO.DAOValidacion;
import Egreso.Validador.DAO.MemoriaValidacion;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Rol.Acciones.LeerMensaje;
import Rol.Acciones.RevisarBandeja;
import Rol.Mensajero;
import Rol.RolRevisorCompra;
import Usuario.Usuario;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ValidadorDeOperacion {

    static DAOValidacion repositorio = new MemoriaValidacion();

    static List<ValidacionOperacion> validaciones = repositorio.obtenerValidaciones();

    static EstrategiaRevision estrategia;

    public static DAOValidacion getRepositorio() {
        return repositorio;
    }

    public static List<ValidacionOperacion> getValidaciones() {
        return validaciones;
    }

    public static EstrategiaRevision getEstrategia() {
        return estrategia;
    }

    //este seria con roles compartidos donde devuelvo el msj
    public static Mensaje validarCustomSinBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) {
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(new Date(), null, "Paso exitosamente todas las Validaciones"));
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                mensaje.set(new Mensaje(new Date(), null, e.toString()));
            }
        });
        return mensaje.get();
    }

    public static void validarCustomConBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validarDefault(unaOperacion);
        validarCustomSinBasicas(unaOperacion, validacionesEspecificas,unUsuario);
    }

    public static void asignarRevisorA(Egreso unaOperacion, Usuario revisor) {
        estrategia.revisar(unaOperacion,revisor);
    }

    public static void validarDefault(Egreso unaOperacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(new Date(), null, "Paso exitosamente todas las Validaciones"));
        validaciones.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                 mensaje.set(new Mensaje(new Date(), null, e.toString()));
            }
        });
        //esto es para roles compartidos
        //return mensaje.get();


        //esto es con roles independientes
        List<RolRevisorCompra> revisores=Mensajero.obtenerRevisoresDe(unaOperacion);
        revisores.forEach(rol->enviarMensaje(rol,mensaje.get()));
    }

    private static void enviarMensaje(RolRevisorCompra rol, Mensaje mensaje) {
       RevisarBandeja accionRevisor= (RevisarBandeja) rol.acciones.stream().filter(accion -> accion.getClass().equals(RevisarBandeja.class)).collect(Collectors.toList()).get(0);
       rol.acciones.add(new LeerMensaje(mensaje));
       accionRevisor.getBandejaAsociada().agregarMensaje(mensaje);
    }


}
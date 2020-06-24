package Egreso.Validador;

import BandejaMensajes.Mensaje;
import Egreso.Core.Egreso;
import Egreso.Validador.DAO.DAOValidacion;
import Egreso.Validador.DAO.MemoriaValidacion;
import Egreso.Validador.EstrategiasRevision.EstrategiaRevision;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.Validaciones.ValidacionOperacion;
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

    private static DAOValidacion repositorio = new MemoriaValidacion();
    private static List<ValidacionOperacion> validaciones = repositorio.obtenerValidaciones();
    private static EstrategiaRevision estrategia;

    public static DAOValidacion getRepositorio() {
        return repositorio;
    }
    public static List<ValidacionOperacion> getValidaciones() {
        return validaciones;
    }
    public static EstrategiaRevision getEstrategia() {
        return estrategia;
    }
    public static Mensaje validarCustomSinBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas) {
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(new Date(), null, "Paso exitosamente todas las Validaciones"));
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
                unaOperacion.setEstaVerificada(true);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                mensaje.set(new Mensaje(new Date(), null, e.toString()));
            }
        });
        return mensaje.get();
    }

    public static void validarCustomConBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validarDefault(unaOperacion);
        validarCustomSinBasicas(unaOperacion, validacionesEspecificas);
    }

    public static void validarDefault(Egreso unaOperacion){
        Mensaje mensaje= validarCustomSinBasicas(unaOperacion,validaciones);
        List<RolRevisorCompra> revisores=Mensajero.obtenerRevisoresDe(unaOperacion);
        revisores.forEach(rol->enviarMensaje(rol,mensaje));
    }

    private static void enviarMensaje(RolRevisorCompra rol, Mensaje mensaje) {
       RevisarBandeja accionRevisor= (RevisarBandeja) rol.getAcciones().stream().filter(accion -> accion.getClass().equals(RevisarBandeja.class)).collect(Collectors.toList()).get(0);
       rol.getAcciones().add(new LeerMensaje(mensaje));
       accionRevisor.getBandejaAsociada().agregarMensaje(mensaje);
    }

}
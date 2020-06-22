package Egreso.Validador;

import BandejaMensajes.Mensaje;
import Egreso.Core.Egreso;
import Egreso.Validador.DAO.DAOValidacion;
import Egreso.Validador.DAO.MemoriaValidacion;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Usuario.Usuario;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ValidadorDeOperacion {

    static DAOValidacion repositorio = new MemoriaValidacion();

    static List<ValidacionOperacion> validaciones = repositorio.obtenerValidaciones();

    static EstrategiaRevision estrategia;

    public static Mensaje validarCustomSinBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) {
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(new Date(), null, "Paso exitosamente todas las Validaciones", false));
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                mensaje.set(new Mensaje(new Date(), null, e.toString(), false));
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

    public static Mensaje validarDefault(Egreso unaOperacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(new Date(), null, "Paso exitosamente todas las Validaciones", false));
        validaciones.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                 mensaje.set(new Mensaje(new Date(), null, e.toString(), false));
            }
        });
        return mensaje.get();

    }



}
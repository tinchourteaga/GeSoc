package Egreso.Validador;

import Egreso.Core.Egreso;
import Egreso.Validador.DAO.DAOValidacion;
import Egreso.Validador.DAO.MemoriaValidacion;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Usuario.Usuario;

import java.util.List;

public class ValidadorDeOperacion {

    static DAOValidacion repositorio = new MemoriaValidacion();

    static List<ValidacionOperacion> validaciones = repositorio.obtenerValidaciones();

    static EstrategiaRevision estrategia;

    public static void validarCustomSinBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) {
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                agregarValidacionFallida(unaOperacion);
            }
        });
        agregarValidacionExitosa(unaOperacion);
    }

    public static void validarCustomConBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validarDefault(unaOperacion);
        validarCustomSinBasicas(unaOperacion, validacionesEspecificas,unUsuario);
    }

    public static void asignarRevisorA(Egreso unaOperacion, Usuario revisor) {
        estrategia.revisar(unaOperacion,revisor);
    }

    public static void validarDefault(Egreso unaOperacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validaciones.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                agregarValidacionFallida(unaOperacion);
            }
        });

        agregarValidacionExitosa(unaOperacion);
    }

    public static void agregarValidacionExitosa(Egreso unaOperacion){
        //Creo el msj que ahora es una clase
    }
    public static void agregarValidacionFallida(Egreso unaOperacion){
        //Creo el msj que ahora es una clase
    }



}
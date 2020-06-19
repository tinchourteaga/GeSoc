package Operacion.Validador;

import Operacion.Core.Operacion;
import Operacion.Validador.DAO.DAOValidacion;
import Operacion.Validador.DAO.MemoriaValidacion;
import Operacion.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeOperacion {

    static DAOValidacion repositorio = new MemoriaValidacion();

    static List<ValidacionOperacion> validaciones = repositorio.obtenerValidaciones();

    static EstrategiaRevision estrategia;

    public static void validarCustomSinBasicas(Operacion unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) {
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                agregarValidacionFallida(unaOperacion);
            }
        });
        agregarValidacionExitosa(unaOperacion);
    }

    public static void validarCustomConBasicas(Operacion unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validarDefault(unaOperacion);
        validarCustomSinBasicas(unaOperacion, validacionesEspecificas,unUsuario);
    }

    public static void asignarRevisorA(Operacion unaOperacion, Usuario revisor) {
        estrategia.revisar(unaOperacion,revisor);
    }

    public static void validarDefault(Operacion unaOperacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validaciones.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                agregarValidacionFallida(unaOperacion);
            }
        });

        agregarValidacionExitosa(unaOperacion);
    }

    public static void agregarValidacionExitosa(Operacion unaOperacion){
        //Creo el msj que ahora es una clase
    }
    public static void agregarValidacionFallida(Operacion unaOperacion){
        //Creo el msj que ahora es una clase
    }



}
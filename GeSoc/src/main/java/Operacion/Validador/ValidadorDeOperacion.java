package Operacion.Validador;

import Operacion.Core.Operacion;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeOperacion {
    static List<ValidacionOperacion> validaciones = new ArrayList() {{

    }};
    static EstrategiaRevision estrategia;
    static List<Mensaje> mensajes = new ArrayList();

    public static void validarCustomSinBasicas(Operacion unaOperacion, List<ValidacionOperacion> validacionesEspecificas) {
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException e) {
                agregarValidacionFallida(unaOperacion);
            }
        });
        agregarValidacionExitosa(unaOperacion);
    }

    public static void validarCustomConBasicas(Operacion unaOperacion, List<ValidacionOperacion> validacionesEspecificas) {
        validarDefault(unaOperacion);
        validarCustomSinBasicas(unaOperacion, validacionesEspecificas);
    }

    public static void asignarRevisorA(Operacion unaOperacion, Usuario revisor) {
        estrategia.revisar(unaOperacion,revisor);
    }

    public static void validarDefault(Operacion unaOperacion) {
        validaciones.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException e) {
                agregarValidacionFallida(unaOperacion);
            }
        });
        agregarValidacionExitosa(unaOperacion);
    }
    public static void agregarValidacionExitosa(Operacion unaOperacion){
        //creo el msj que ahora es una clase
    }
    public static void agregarValidacionFallida(Operacion unaOperacion){
        //creo el msj que ahora es una clase
    }
}
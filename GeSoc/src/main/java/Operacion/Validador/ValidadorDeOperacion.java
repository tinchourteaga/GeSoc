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

    public static void validarCustomSinBasicas(Operacion unaOperacion, List<ValidacionOperacion> validacionesEspecificas) {
        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException e) {
                e.printStackTrace();
            }
        });
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
                e.printStackTrace();
            }
        });
    }
}
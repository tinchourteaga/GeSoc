package Operacion.Validador.Excepciones;

public class NoCumpleValidacionException extends Exception {
    public NoCumpleValidacionException(){
        super("El usuario se equivoco de contrasenia cuando quiso cambiarla");
    }

}



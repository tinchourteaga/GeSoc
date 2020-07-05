package Dominio.Egreso.Validador.Excepciones;

public class NoCumpleValidacionException extends Exception {
    public NoCumpleValidacionException(){
        super("No cumple con la validacion requerida");
    }

}



package Validacion.Excepciones;

public class ExcepcionLongitud extends Exception{
    public ExcepcionLongitud(){
        super("La contrasenia no tiene una longitud suficiente");
    }
}

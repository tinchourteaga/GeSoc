package Dominio.Contrasenia.Excepciones;

public class ExcepcionLongitud extends Exception{
    public ExcepcionLongitud(){
        super("La contrasenia no tiene una longitud suficiente");
    }
}

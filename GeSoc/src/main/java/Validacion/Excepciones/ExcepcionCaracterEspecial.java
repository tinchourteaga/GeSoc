package Validacion.Excepciones;

public class ExcepcionCaracterEspecial extends Exception{
    public ExcepcionCaracterEspecial(){
        super("La contrasenia no tiene ningun caracter especial");
    }
}
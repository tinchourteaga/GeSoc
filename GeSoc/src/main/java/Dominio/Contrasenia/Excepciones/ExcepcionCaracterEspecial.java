package Dominio.Contrasenia.Excepciones;

public class ExcepcionCaracterEspecial extends Exception{
    public ExcepcionCaracterEspecial(){
        super("La contrasenia no tiene ningun caracter especial");
    }
}
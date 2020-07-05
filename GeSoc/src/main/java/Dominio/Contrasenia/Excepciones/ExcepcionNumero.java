package Dominio.Contrasenia.Excepciones;

public class ExcepcionNumero extends Exception{
    public ExcepcionNumero(){
        super("La contrasenia no tiene numeros");
    }
}
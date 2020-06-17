package Rol.Exepciones;

public class ContraseniasDistintasException extends Exception {
    public ContraseniasDistintasException(){
        super("El usuario se equivoco de contrasenia cuando quiso cambiarla");
    }

}

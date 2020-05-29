package Validacion.Excepciones;

public class ExcepcionContraseniaComun extends Exception{
    public ExcepcionContraseniaComun(){
        super("La contrasenia es muy comun");
    }
}

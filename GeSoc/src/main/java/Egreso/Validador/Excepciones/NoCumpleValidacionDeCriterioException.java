package Egreso.Validador.Excepciones;

public class NoCumpleValidacionDeCriterioException extends Exception{
    public NoCumpleValidacionDeCriterioException(){
        super("La validacion no concuerda con el criterio establecido");
    }
}

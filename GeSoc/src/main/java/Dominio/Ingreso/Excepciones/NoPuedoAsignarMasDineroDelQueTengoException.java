package Dominio.Ingreso.Excepciones;

public class NoPuedoAsignarMasDineroDelQueTengoException extends Exception{
    public NoPuedoAsignarMasDineroDelQueTengoException(){
        super("No Puedo Asignar dicho monto porque el total excederia el total del ingreso previsto");
    }
    
}

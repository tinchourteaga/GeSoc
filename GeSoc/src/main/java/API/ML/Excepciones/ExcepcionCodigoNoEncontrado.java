package API.ML.Excepciones;

public class ExcepcionCodigoNoEncontrado extends Exception {
    public ExcepcionCodigoNoEncontrado(String nombrePais, String zipCode) {
        super("En el pais "+nombrePais+" no se encontro el codigo postal indicado ("+zipCode+")");
    }
}

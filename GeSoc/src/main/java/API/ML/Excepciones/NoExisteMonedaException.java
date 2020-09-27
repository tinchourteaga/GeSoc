package API.ML.Excepciones;

public class NoExisteMonedaException extends Throwable {
    public NoExisteMonedaException(String nombreMoneda) {
        super("No se encontraron datos de la moneda "+ nombreMoneda);
    }
}

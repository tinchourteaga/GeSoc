package API.Excepciones;

public class ExcepcionNoSePudoConvertir extends Exception {
    public ExcepcionNoSePudoConvertir(String nombreMonedaActual, String nombreMonedaAConvertir) {
        super("No se pudo convertir de "+nombreMonedaActual+" a "+nombreMonedaAConvertir);
    }
}

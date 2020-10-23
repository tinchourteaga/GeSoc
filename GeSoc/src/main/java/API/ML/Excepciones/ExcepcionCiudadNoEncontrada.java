package API.ML.Excepciones;

public class ExcepcionCiudadNoEncontrada extends Exception {
    public ExcepcionCiudadNoEncontrada(String id) {
        super("No se pudo encontrar la Ciudad/city con id: "+id);
    }
}

package API.ML.Excepciones;

public class ExcepcionProvinciaNoEncontrada extends Exception {
    public ExcepcionProvinciaNoEncontrada(String id) {
        super("No se pudo encontrar la Provincia/State con id: "+id);
    }
}

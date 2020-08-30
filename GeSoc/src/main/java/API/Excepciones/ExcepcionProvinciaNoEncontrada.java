package API.Excepciones;

public class ExcepcionProvinciaNoEncontrada extends Throwable {
    public ExcepcionProvinciaNoEncontrada(String id) {
        super("No se pudo encontrar la Provincia/State con id: "+id);
    }
}

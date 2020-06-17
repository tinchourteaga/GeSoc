package BandejaMensajes;

public class Mensaje {
    String fechaCreado;
    String fechaLeido;
    String mensajeResultado;
    Boolean leido = false;

    public Mensaje(String fechaCreado, String fechaLeido, String mensajeResultado, Boolean leido) {
        this.fechaCreado = fechaCreado;
        this.fechaLeido = fechaLeido;
        this.mensajeResultado = mensajeResultado;
        this.leido = leido;
    }

    public void leer(){

    }
}

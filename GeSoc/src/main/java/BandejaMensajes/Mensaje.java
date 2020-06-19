package BandejaMensajes;

import java.util.Date;

public class Mensaje {
    Date fechaCreado;
    Date fechaLeido;
    String mensajeResultado;
    Boolean leido = false;

    public Mensaje(Date fechaCreado, Date fechaLeido, String mensajeResultado, Boolean leido) {
        this.fechaCreado = fechaCreado;
        this.fechaLeido = fechaLeido;
        this.mensajeResultado = mensajeResultado;
        this.leido = leido;
    }

    public void leer(){
        this.fechaLeido = new Date();
    }
}

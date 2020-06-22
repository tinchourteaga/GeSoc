package BandejaMensajes;

import java.util.Date;

public class Mensaje {
    Date fechaCreado;
    Date fechaLeido;
    String mensajeResultado;
    Boolean leido = false;

    public Mensaje(Date fechaCreado, Date fechaLeido, String mensajeResultado) {
        this.fechaCreado = fechaCreado;
        this.fechaLeido = fechaLeido;
        this.mensajeResultado = mensajeResultado;
        this.leido = false;
    }

    public void leer(){
        this.fechaLeido = new Date();
        this.leido=true;
    }
}

package BandejaMensajes;

import java.util.Date;

public class Mensaje {
    private Date fechaCreado;
    private Date fechaLeido;
    private String mensajeResultado;
    private Boolean leido;

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

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public Date getFechaLeido() {
        return fechaLeido;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public Boolean getLeido() {
        return leido;
    }
}

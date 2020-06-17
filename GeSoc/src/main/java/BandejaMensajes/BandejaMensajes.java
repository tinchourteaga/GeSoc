package BandejaMensajes;

import java.util.ArrayList;
import java.util.List;

public class BandejaMensajes {

    private List<Mensaje> mensajes;

    public BandejaMensajes() {
        this.mensajes = new ArrayList<Mensaje>();
    }

    public void filtrarPorFecha(){

    }

    public void filtrarPorLeidos(){

    }

    public void agregarMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    public void removerMensaje(Mensaje mensaje){
        mensajes.remove(mensaje);
    }

}

package BandejaMensajes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BandejaMensajes {

    private List<Mensaje> mensajes;

    public BandejaMensajes() {
        this.mensajes = new ArrayList<Mensaje>();
    }

    public List<Mensaje> filtrarPorFecha(Date fecha) {
    return mensajes.stream().filter(mensaje-> mensaje.fechaCreado.getTime()>=fecha.getTime()).collect(Collectors.toList());

    }

    public List<Mensaje> filtrarPorLeidos(){
        List<Mensaje> mensajesLeidos = new ArrayList<Mensaje>();
        mensajesLeidos =this.mensajes.stream()
                     .filter(x -> x.fechaLeido != null )
                     .collect(Collectors.toList());
	    return mensajesLeidos;
    }

    public void agregarMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    public void removerMensaje(Mensaje mensaje){
        mensajes.remove(mensaje);
    }

}

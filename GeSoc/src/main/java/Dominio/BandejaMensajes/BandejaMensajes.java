package Dominio.BandejaMensajes;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class BandejaMensajes {
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    public BandejaMensajes() {
        this.mensajes = new ArrayList<Mensaje>();
    }

    public List<Mensaje> filtrarPorFecha(LocalDate fecha) {
        return mensajes.stream().filter(mensaje-> fecha.isBefore(mensaje.getFechaCreado())).collect(Collectors.toList());
    }

    public List<Mensaje> filtrarPorLeidos(){
        List<Mensaje> mensajesLeidos = new ArrayList<Mensaje>();
        mensajesLeidos =this.mensajes.stream()
                     .filter(x -> x.getFechaLeido() != null )
                     .collect(Collectors.toList());
	    return mensajesLeidos;
    }

    public void agregarMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    public void removerMensaje(Mensaje mensaje){
        mensajes.remove(mensaje);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
}

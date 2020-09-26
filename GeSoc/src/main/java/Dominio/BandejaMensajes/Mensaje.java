package Dominio.BandejaMensajes;

import Dominio.Usuario.Usuario;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "men_mensajes")
public class Mensaje {
    @Id
    @GeneratedValue
    private int mensaje;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    private Usuario usuario;

    @Column(name = "cuerpo_mensaje")
    private String mensajeResultado;

    @Column(name = "fecha_creado", columnDefinition = "DATE")
    private LocalDate fechaCreado;

    @Column(name = "fecha_leido", columnDefinition = "DATE")
    private LocalDate fechaLeido;

    @Column(name = "leido")
    private Boolean leido;

    public Mensaje(LocalDate fechaCreado, LocalDate fechaLeido, String mensajeResultado) {
        this.fechaCreado = fechaCreado;
        this.fechaLeido = fechaLeido;
        this.mensajeResultado = mensajeResultado;
        this.leido = false;
    }

    public void leer(){
        this.fechaLeido = LocalDate.now();
        this.leido=true;
    }

    public LocalDate getFechaCreado() { return fechaCreado; }

    public LocalDate getFechaLeido() {
        return fechaLeido;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public Boolean getLeido() {
        return leido;
    }
}

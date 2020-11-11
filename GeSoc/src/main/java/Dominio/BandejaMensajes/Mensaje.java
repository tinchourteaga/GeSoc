package Dominio.BandejaMensajes;

import Converters.LocalDateAttributeConverter;
import Dominio.Egreso.Core.Egreso;
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

    @Lob
    @Column(name = "cuerpo_mensaje", length = 15000)
    private String mensajeResultado;

    @Column(name = "fecha_creado")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fechaCreado;

    @Column(name = "fecha_leido")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fechaLeido;

    @Column(name = "leido")
    private Boolean leido;

    @ManyToOne
    @JoinColumn(name = "egreso_asociado")
    Egreso egreso;

    public Mensaje() { }

    public Mensaje(LocalDate fechaCreado, LocalDate fechaLeido, String mensajeResultado, Egreso egreso) {
        this.fechaCreado = fechaCreado;
        this.fechaLeido = fechaLeido;
        this.mensajeResultado = mensajeResultado;
        this.leido = false;
        this.egreso = egreso;
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

    public Egreso getEgreso() {
        return egreso;
    }
}

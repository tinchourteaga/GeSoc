package Dominio.Usuario;

import Dominio.BandejaMensajes.BandejaMensajes;
import Dominio.Contrasenia.Core.ValidadorDeContrasenia;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Egreso.Core.Egreso;
import Dominio.Entidad.Entidad;
import Dominio.Rol.Exepciones.ContraseniasDistintasException;
import Servidor.Controllers.ControllerNombreUsuario;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pers_usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private int usuario;

    @Column(name = "nickname")
    private String persona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dni")
    private String dni;

    @Column(name = "mail")
    private String mail;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "clave")
    private String contrasenia;

    @Column(name = "rol")
    @Enumerated(value = EnumType.STRING)
    private Rol rol; //Estandar o Administrador

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dom_rol_dom_egresos",
            joinColumns = { @JoinColumn(name = "rol") },
            inverseJoinColumns = { @JoinColumn(name = "egreso") }
    )
    protected List<Egreso> egresosARevisar=new ArrayList();

    public List<Egreso> getEgresosAREvisar() {
        return this.egresosARevisar;
    }

    public void agregarEgresoARevisar(Egreso egreso) {
        this.egresosARevisar.add(egreso);
    }

    public void setEntidades(List<Entidad> entidades) { this.entidades = entidades; }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pers_usuarios_dom_entidades",
            joinColumns = { @JoinColumn(name = "usuario") },
            inverseJoinColumns = { @JoinColumn(name = "entidad") }
    )
    protected List<Entidad> entidades = new ArrayList<>();

    public List<Entidad> getEntidades() { return this.entidades; }

    public void agregarEntidades(Entidad entidad) { this.entidades.add(entidad);}

    @Embedded
    private BandejaMensajes bandejaDeMensajes;

    @Column(name = "nro_celular")
    private String celular;

    public Usuario() { }

    public Usuario(Rol rol, String nombre, String apellido, String contrasenia, String dni, String mail) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        ValidadorDeContrasenia.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
        this.bandejaDeMensajes = new BandejaMensajes();
    }

    public void setPersona() { this.persona = ControllerNombreUsuario.otorgarNombreUsuario(this);
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol unRol){
        this.rol = unRol;
    }

    public BandejaMensajes getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public int getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
   // public void setContrasenia(String contrasenia){ this.contrasenia = contrasenia; }

    public void setContrasenia (String contraseniaNueva) throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        if(ValidadorDeContrasenia.validarContrasenia(contraseniaNueva)){
            this.contrasenia = contraseniaNueva;
        }
    }

    public String getNickName() {
        return persona;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void cambiarContrasenia(String contraseniaAnterior, String contraseniaNueva) throws ContraseniasDistintasException, IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        if (this.contrasenia.equals(contraseniaAnterior)){
            this.setContrasenia(contraseniaNueva);
        }else{
            throw new ContraseniasDistintasException();
        }
    }

}
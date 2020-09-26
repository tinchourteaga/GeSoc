package Dominio.Usuario;

import Dominio.BandejaMensajes.BandejaMensajes;
import Dominio.Contrasenia.Core.ValidadorDeContrasenia;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.Exepciones.ContraseniasDistintasException;
import Dominio.Rol.Rol;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "pers_usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private int usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "clave")
    private String contrasenia;

   // @Column(name = "rol", columnDefinition = "ROL")
    @Transient
    private Rol rol;

    @Embedded
    private BandejaMensajes bandejaDeMensajes;

    public Usuario(Rol rol, String nombre, String apellido, String contrasenia) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        ValidadorDeContrasenia.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
        this.bandejaDeMensajes = new BandejaMensajes();
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
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

    public String getContrasenia() {
        return contrasenia;
    }

    private void setContrasenia (String contraseniaNueva) throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        if(ValidadorDeContrasenia.validarContrasenia(contraseniaNueva)){
            this.contrasenia = contraseniaNueva;
        }
    }

    public void cambiarContrasenia(String contraseniaAnterior, String contraseniaNueva) throws ContraseniasDistintasException, IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        if (this.contrasenia.equals(contraseniaAnterior)){
            this.setContrasenia(contraseniaNueva);
        }else{
            throw new ContraseniasDistintasException();
        }
    }
}
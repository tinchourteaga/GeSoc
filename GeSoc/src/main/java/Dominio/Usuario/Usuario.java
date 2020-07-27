package Dominio.Usuario;

import java.io.IOException;
import java.util.List;

import Dominio.BandejaMensajes.BandejaMensajes;
import Dominio.Contrasenia.Core.ValidadorDeContrasenia;
import Dominio.Rol.Rol;
import Dominio.Rol.Exepciones.ContraseniasDistintasException;
import Dominio.Contrasenia.Excepciones.*;

public class Usuario {

    private Rol rol;
    private String nombre;
    private String contrasenia;
    private BandejaMensajes bandejaDeMensajes;

    public Usuario(Rol rol, String nombre, String contrasenia) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
        this.rol = rol;
        this.nombre = nombre;
        ValidadorDeContrasenia.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
        this.bandejaDeMensajes = new BandejaMensajes();
    }

    public String getNombre() {
        return nombre;
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
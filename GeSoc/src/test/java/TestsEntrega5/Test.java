package TestsEntrega5;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.Administrador;
import Dominio.Rol.Estandar;
import Dominio.Usuario.Usuario;
import Persistencia.RepositorioUsuario;

import java.io.IOException;

public class Test {
    @org.junit.Test
    public void persistirUsuarioEstandar() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        Estandar rolPrueba = new Estandar();
        String nombre = "pruebita";
        String apellido = "normal";
        Usuario usuario = new Usuario(rolPrueba, nombre, apellido,"chaybsafsa");
        usuario.setPersona(nombre, apellido);
        RepositorioUsuario.getInstance().agregar(usuario);
    }

    @org.junit.Test
    public void persistirUsuarioAdmin() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        Administrador rolPrueba = new Administrador();
        String nombre = "pepito";
        String apellido = "fachero";
        Usuario usuario= new Usuario(rolPrueba,nombre, apellido, "holaslfmaf");
        usuario.setPersona(nombre, apellido);
        RepositorioUsuario.getInstance().agregar(usuario);
    }
}

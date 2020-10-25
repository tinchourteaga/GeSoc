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
        Usuario usuario= new Usuario(rolPrueba,"pruebita", "normal","S4R4z@78P212EyR");
        RepositorioUsuario.getInstance().agregar(usuario);
    }

    @org.junit.Test
    public void persistirUsuarioAdmin() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        Administrador rolPrueba = new Administrador();
        Usuario usuario= new Usuario(rolPrueba,"pepito", "fachero", "holaslfmaf");
        RepositorioUsuario.getInstance().agregar(usuario);
    }
}

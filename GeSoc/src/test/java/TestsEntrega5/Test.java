package TestsEntrega5;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.RolAdministrador;
import Dominio.Rol.RolEstandar;
import Dominio.Usuario.Usuario;
import Persistencia.RepositorioUsuario;

import java.io.IOException;

public class Test {
    @org.junit.Test
    public void persistirUsuarioEstandar() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        RolEstandar rolPrueba = new RolEstandar();
        Usuario usuario= new Usuario(rolPrueba,"pruebita", "normal","S4R4z@78P212EyR");
        RepositorioUsuario.getInstance().agregar(usuario);
    }

    @org.junit.Test
    public void persistirUsuarioAdmin() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        RolAdministrador rolPrueba = new RolAdministrador();
        Usuario usuario= new Usuario(rolPrueba,"pepito", "fachero","S4R4z@78P212EyR");
        RepositorioUsuario.getInstance().agregar(usuario);
    }
}

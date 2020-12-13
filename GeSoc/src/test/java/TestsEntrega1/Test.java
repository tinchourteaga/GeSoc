package TestsEntrega1;

import Dominio.Contrasenia.Core.*;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.Exepciones.ContraseniasDistintasException;
import Dominio.Usuario.Rol;
import Dominio.Usuario.Usuario;
import org.junit.Assert;

import java.io.IOException;

public class Test {

    Rol rolPrueba = Rol.ADMINISTRADOR;

/*    @org.junit.Test(expected = ExcepcionNumero.class)
    public void testContraseniaSinNumeros() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ExcepcionCaracterEspecial, ExcepcionLongitud, ExcepcionContraseniaComun {
        ContieneNumero validacion1 =new ContieneNumero();
        validacion1.validar("saraza");
    }
    @org.junit.Test(expected = ExcepcionLongitud.class)
    public void testContraseniaMuyCorta() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ChequearLongitudContrasenia validacion1 =new ChequearLongitudContrasenia();
        validacion1.validar("a");
    }
    @org.junit.Test(expected = ExcepcionCaracterEspecial.class)
    public void testContraseniaSinCaracterEspecial() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ContieneCaracterEspecial validacion1 =new ContieneCaracterEspecial();
        validacion1.validar("saraza");
    }
    @org.junit.Test(expected = ExcepcionContraseniaComun.class)
    public void testContraseniaComun() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        validacion1.validar("qwerty");
    }
*/
    @org.junit.Test
    public void testContraseniaCorrecta() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        ChequearLongitudContrasenia validacion2 =new ChequearLongitudContrasenia();
        ContieneNumero validacion3 =new ContieneNumero();
        ContieneCaracterEspecial validacion4 =new ContieneCaracterEspecial();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.agregarValidacion(validacion2);
        ValidadorDeContrasenia.agregarValidacion(validacion3);
        ValidadorDeContrasenia.agregarValidacion(validacion4);
        ValidadorDeContrasenia.validarContrasenia("S4R4z@78P212EyR");
    }

    @org.junit.Test
    public void testContraseniaCambiadaASinNumeros() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {

        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        ChequearLongitudContrasenia validacion2 =new ChequearLongitudContrasenia();
        ContieneNumero validacion3 =new ContieneNumero();
        ContieneCaracterEspecial validacion4 =new ContieneCaracterEspecial();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.agregarValidacion(validacion2);
        ValidadorDeContrasenia.agregarValidacion(validacion3);
        ValidadorDeContrasenia.agregarValidacion(validacion4);
        Usuario usuario= new Usuario(rolPrueba,"pepito", "fachero","S4R4z@78P212EyR","41658239", "mail@mail.com");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","sarasa");
        Assert.assertEquals(usuario.getContrasenia(),"S4R4z@78P212EyR");
    }
    @org.junit.Test
    public void testContraseniaCambiadaAMuyCorta() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {
        ChequearLongitudContrasenia validacion1 =new ChequearLongitudContrasenia();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        Usuario usuario= new Usuario(rolPrueba,"pepito", "fachero","S4R4z@78P212EyR", "41658239", "mail@mail.com");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","s");
        Assert.assertEquals(usuario.getContrasenia(),"S4R4z@78P212EyR");
    }
    @org.junit.Test
    public void testContraseniaCambiadaASinCaracterEspecial() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {
        ContieneCaracterEspecial validacion1 =new ContieneCaracterEspecial();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        Usuario usuario= new Usuario(rolPrueba,"pepito", "fachero","S4R4z@78P212EyR", "41658239", "mail@mail.com");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","sarasa");
        Assert.assertEquals(usuario.getContrasenia(),"S4R4z@78P212EyR");
    }
    @org.junit.Test
    public void testContraseniaCambiadaAComun() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {
        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        Usuario usuario= new Usuario(rolPrueba,"pepito", "fachero","S4R4z@78P212EyR", "41658239", "mail@mail.com");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","qwerty");
        Assert.assertEquals(usuario.getContrasenia(),"S4R4z@78P212EyR");

    }

}

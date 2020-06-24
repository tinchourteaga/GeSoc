package TestsEntrega1;

import Contrasenia.Core.*;
import Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Contrasenia.Excepciones.ExcepcionLongitud;
import Contrasenia.Excepciones.ExcepcionNumero;
import Rol.Exepciones.ContraseniasDistintasException;
import Usuario.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    @org.junit.Test(expected = ExcepcionNumero.class)
    public void TestContraseniaSinNumeros() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ContieneNumero validacion1 =new ContieneNumero();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.validarContrasenia("saraza");
    }
    @org.junit.Test(expected = ExcepcionLongitud.class)
    public void TestContraseniaMuyCorta() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ChequearLongitudContrasenia validacion1 =new ChequearLongitudContrasenia();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.validarContrasenia("a");
    }
    @org.junit.Test(expected = ExcepcionCaracterEspecial.class)
    public void TestContraseniaSinCaracterEspecial() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ContieneCaracterEspecial validacion1 =new ContieneCaracterEspecial();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.validarContrasenia("saraza");
    }
    @org.junit.Test(expected = ExcepcionContraseniaComun.class)
    public void TestContraseniaComun() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.validarContrasenia("qwerty");
    }

    @org.junit.Test
    public void TestContraseniaCorrecta() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
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




    @org.junit.Test(expected = ExcepcionNumero.class)
    public void TestContraseniaCambiadaASinNumeros() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {

        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        ChequearLongitudContrasenia validacion2 =new ChequearLongitudContrasenia();
        ContieneNumero validacion3 =new ContieneNumero();
        ContieneCaracterEspecial validacion4 =new ContieneCaracterEspecial();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        ValidadorDeContrasenia.agregarValidacion(validacion2);
        ValidadorDeContrasenia.agregarValidacion(validacion3);
        ValidadorDeContrasenia.agregarValidacion(validacion4);
        Usuario usuario= new Usuario(new ArrayList<>(),"pepito","S4R4z@78P212EyR");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","sarasa");
    }
    @org.junit.Test(expected = ExcepcionLongitud.class)
    public void TestContraseniaCambiadaAMuyCorta() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {
        ChequearLongitudContrasenia validacion1 =new ChequearLongitudContrasenia();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        Usuario usuario= new Usuario(new ArrayList<>(),"pepito","S4R4z@78P212EyR");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","s");
    }
    @org.junit.Test(expected = ExcepcionCaracterEspecial.class)
    public void TestContraseniaCambiadaASinCaracterEspecial() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {
        ContieneCaracterEspecial validacion1 =new ContieneCaracterEspecial();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        Usuario usuario= new Usuario(new ArrayList<>(),"pepito","S4R4z@78P212EyR");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","sarasa");
    }
    @org.junit.Test(expected = ExcepcionContraseniaComun.class)
    public void TestContraseniaCambiadaAComun() throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException, ContraseniasDistintasException {
        ChequearContraseniaComun validacion1 =new ChequearContraseniaComun();
        ValidadorDeContrasenia.removerTodasLasValidaciones();
        ValidadorDeContrasenia.agregarValidacion(validacion1);
        Usuario usuario= new Usuario(new ArrayList<>(),"pepito","S4R4z@78P212EyR");
        usuario.cambiarContrasenia("S4R4z@78P212EyR","qwerty");

    }




}

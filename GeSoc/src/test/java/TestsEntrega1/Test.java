package TestsEntrega1;

import Contrasenia.Core.*;
import Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Contrasenia.Excepciones.ExcepcionLongitud;
import Contrasenia.Excepciones.ExcepcionNumero;

import java.io.IOException;

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

}

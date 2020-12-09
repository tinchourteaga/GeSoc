package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class ValidadorDeContrasenia {

    /*
    private static DAO repositorio = new DAOMemoria<IValidacion>();
    //singleton class
    private static List<IValidacion> validaciones = repositorio.getAllElementos();

     */

    private static List<IValidacion> validaciones = new ArrayList<>();

    public static boolean validarContrasenia(String contrasenia) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {

        AtomicBoolean esSegura = new AtomicBoolean(true);

        try {
            ChequearContraseniaComun.validar(contrasenia);
            ChequearLongitudContrasenia.validar(contrasenia);
            ContieneNumero.validar(contrasenia);
            ContieneCaracterEspecial.validar(contrasenia);

        }  catch (ExcepcionContraseniaComun | ExcepcionLongitud | ExcepcionNumero | ExcepcionCaracterEspecial e) {
            e.printStackTrace();
            esSegura.set(false);
        }

        return esSegura.get();
    }
    public static void agregarValidacion(IValidacion nuevaValidacion) {
        validaciones.add(nuevaValidacion);
    }

    public static void removerValidacion(IValidacion viejaValidacion) {
        validaciones.remove(viejaValidacion);
    }

    public static void removerTodasLasValidaciones(){ validaciones.clear(); }

    public static void AgregarTodasLasValidaciones(List<IValidacion>validacionesNuevas){
        validaciones.addAll(validacionesNuevas);
    }

}

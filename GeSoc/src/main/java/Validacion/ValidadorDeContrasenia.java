package Validacion;

import Validacion.Excepciones.ExcepcionContraseniaComun;
import Validacion.Excepciones.ExcepcionLongitud;
import Validacion.Excepciones.ExcepcionNumero;
import Validacion.Excepciones.ExcepcionCaracterEspecial;

import java.io.*;
import java.util.*;


public class ValidadorDeContrasenia {
    //singleton class
    private static List<IValidacion> Validaciones = new ArrayList<IValidacion>();

    public static void validarContrasenia(String contrasenia) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        //No me gusta como esta esto
        for (IValidacion validacion : Validaciones) {
            validacion.validar(contrasenia);
        }
    }
    public static void agregarValidacion(IValidacion nuevaValidacion) {
        Validaciones.add(nuevaValidacion);
    }
    public static void removerValidacion(IValidacion viejaValidacion) {
        Validaciones.remove(viejaValidacion);
    }
    public static void removerTodasLasValidaciones(){
        Validaciones= new ArrayList();
    }
    public static void AgregarTodasLasValidaciones(List<IValidacion>validacioensNuevas){
        Validaciones.addAll(validacioensNuevas);
    }

}

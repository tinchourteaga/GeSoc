package Contrasenia.Core;

import Contrasenia.DAO.DAOValidacion;
import Contrasenia.DAO.MemoriaValidacion;
import Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Contrasenia.Excepciones.ExcepcionLongitud;
import Contrasenia.Excepciones.ExcepcionNumero;
import Contrasenia.Excepciones.ExcepcionCaracterEspecial;

import java.io.*;
import java.util.*;


public class ValidadorDeContrasenia {

    private static DAOValidacion repositorio = new MemoriaValidacion();
    //singleton class
    private static List<IValidacion> Validaciones = repositorio.obtenerValidaciones();

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

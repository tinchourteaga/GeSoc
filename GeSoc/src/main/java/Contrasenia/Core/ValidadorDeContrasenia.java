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
    private static List<IValidacion> validaciones = repositorio.obtenerValidaciones();

    public static void validarContrasenia(String contrasenia) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        validaciones.forEach(validacion-> {
            try {
                validacion.validar(contrasenia);
            } catch (ExcepcionContraseniaComun excepcionContraseniaComun) {
                excepcionContraseniaComun.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExcepcionLongitud excepcionLongitud) {
                excepcionLongitud.printStackTrace();
            } catch (ExcepcionNumero excepcionNumero) {
                excepcionNumero.printStackTrace();
            } catch (ExcepcionCaracterEspecial excepcionCaracterEspecial) {
                excepcionCaracterEspecial.printStackTrace();
            }
        });//cuando tengamos presentacion esos prints vuelan
        // y se tranforman en hermosas mariposas digo... pantallas
    }
    public static void agregarValidacion(IValidacion nuevaValidacion) {
        validaciones.add(nuevaValidacion);
    }
    public static void removerValidacion(IValidacion viejaValidacion) {
        validaciones.remove(viejaValidacion);
    }
    public static void removerTodasLasValidaciones(){ validaciones.clear(); }
    public static void AgregarTodasLasValidaciones(List<IValidacion>validacioensNuevas){
        validaciones.addAll(validacioensNuevas);
    }

}

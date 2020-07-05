package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOMemoria;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class ValidadorDeContrasenia {

    private static DAO repositorio = new DAOMemoria<IValidacion>();
    //singleton class
    private static List<IValidacion> validaciones = repositorio.getAllElementos();

    public static boolean validarContrasenia(String contrasenia) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {

        AtomicBoolean esSegura = new AtomicBoolean(true);

        validaciones.forEach(validacion-> {
            try {
                validacion.validar(contrasenia);
            }  catch (IOException | ExcepcionContraseniaComun | ExcepcionLongitud | ExcepcionNumero | ExcepcionCaracterEspecial e) {
                e.printStackTrace();
                esSegura.set(false);
            }
        });//cuando tengamos presentacion esos prints vuelan
        // y se tranforman en hermosas mariposas digo... pantallas

        return esSegura.get();
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

package Validacion;

import Validacion.Excepciones.ExcepcionContraseniaComun;
import Validacion.Excepciones.ExcepcionLongitud;
import Validacion.Excepciones.ExcepcionNumero;
import Validacion.Excepciones.ExcepcionCaracterEspecial;

import java.io.*;
import java.util.*;


public class ValidadorDeContrasenia {
    //singleton class
    private static List<IValidacion> Validaciones = new ArrayList<IValidacion>(){{
        add(new ChequearLongitudContrasenia());
        add(new ChequearContraseniaComun());
        add(new ContieneNumero());
        add(new ContieneCaracterEspecial());}};

    public static void validarContrasenia(String contrasenia) {
        //No me gusta como esta esto
        Validaciones.forEach(validacion -> {
            try {
                validacion.validar(contrasenia);
            } catch(ExcepcionContraseniaComun e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExcepcionLongitud e) {
                e.printStackTrace();
            } catch (ExcepcionNumero e) {
                e.printStackTrace();
            } catch (ExcepcionCaracterEspecial e) {
                e.printStackTrace();
            }
        });
    }
}

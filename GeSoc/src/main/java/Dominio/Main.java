package Dominio;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Persistencia.AddData;
import Servidor.Servidor;

import java.io.IOException;

public class Main {

    public static void main(String[] args){


       String[] arg= {"Y", "Y"};
        args=arg;

            switch (args[0]) {

                case "Y":
                    try {
                        AddData.notAMain(args);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ExcepcionNumero excepcionNumero) {
                        excepcionNumero.printStackTrace();
                    } catch (ExcepcionLongitud excepcionLongitud) {
                        excepcionLongitud.printStackTrace();
                    } catch (ExcepcionCaracterEspecial excepcionCaracterEspecial) {
                        excepcionCaracterEspecial.printStackTrace();
                    } catch (ExcepcionContraseniaComun excepcionContraseniaComun) {
                        excepcionContraseniaComun.printStackTrace();
                    }
                    break;
                case "N":
                    System.out.println("No se inicializara la base de datos");
                    break;
                default:
                    System.out.println("No se reconocio la respuesta. Por favor ingrese correctamente.");
                    break;
            }
        Servidor.levantarServidor();
    }
}
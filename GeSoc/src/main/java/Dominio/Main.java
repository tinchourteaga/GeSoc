package Dominio;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Persistencia.AddData;
import Servidor.Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args){
        int rta=1;
        while(rta!=0) {
            System.out.println("¿Desea Inicializar la base de datos? \nPresione Y o N ");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String resultado= null;
            try {
                resultado = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
            switch (resultado) {

                case "Y":
                    try {
                        AddData.main(args);
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
                    rta=0;
                    break;
                case "N":
                    System.out.println("No se inicializara la base de datos");
                    rta=0;
                    break;
                default:
                    System.out.println("No se reconocio la respuesta. Por favor ingrese correctamente.");
                    break;
            }
        }
        Servidor.levantarServidor();

    }
}
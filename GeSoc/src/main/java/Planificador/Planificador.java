package Planificador;

import Egreso.Core.Egreso;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.ValidadorDeOperacion;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Planificador {

    List<TimerTask> eventos = new ArrayList<>();
    public static  Planificador instancia = null;

    //Singleton Magic
    public static Planificador getPlanificador(){
        if(Planificador.instancia == null){
            Planificador.instancia = new Planificador();
        }
        return Planificador.instancia;
    }

    private Planificador(){
    }

    public void ejecutar(long tiempoDeValidacionEnMiliSegundos, int tiempoDeValidacionEnNanoSegundos,Egreso unaOperacion, Usuario unUsuario){

        TimerTask hiloDeTarea = new TimerTask() {
            @Override
            public void run() {
                try{
                    Thread.sleep(tiempoDeValidacionEnMiliSegundos,tiempoDeValidacionEnNanoSegundos);
                    ValidadorDeOperacion.validarDefault(unaOperacion);
                }
                catch(InterruptedException e){
                    System.out.println(e);
                } catch (NoCumpleValidacionDeCriterioException e) {
                    e.printStackTrace();
                } catch (NoCumpleValidacionException e) {
                    e.printStackTrace();
                }
            }
        };

        eventos.add(hiloDeTarea);
    }

    public void ejecutar(long tiempoDeValidacionEnMiliSegundos,Egreso unaOperacion, Usuario unUsuario){

        this.ejecutar(tiempoDeValidacionEnMiliSegundos,0,unaOperacion,unUsuario);
    }

    public TimerTask proximoAEjecutar(){
        eventos.sort((TimerTask unEvento,TimerTask otroEvento) ->
                (int)(unEvento.scheduledExecutionTime() - otroEvento.scheduledExecutionTime()));
        return eventos.get(0);
    }
}

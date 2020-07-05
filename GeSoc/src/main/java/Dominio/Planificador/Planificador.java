package Dominio.Planificador;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Planificador {

    private List<TimerTask> eventos;
    public static  Planificador instancia = null;

    //Singleton Magic
    public static Planificador getPlanificador(){
        if(Planificador.instancia == null){
            Planificador.instancia = new Planificador();
        }
        return Planificador.instancia;
    }

    private Planificador(){
        this.eventos= new ArrayList<>();
    }

    public void ejecutar(long tiempoDeValidacionEnMiliSegundos, int tiempoDeValidacionEnNanoSegundos,Egreso unaOperacion){

        System.out.println("Estoy a punto de correr el hilo");
        TimerTask hiloDeTarea = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Estoy por entrar al while");
                //Thread.sleep(tiempoDeValidacionEnMiliSegundos, tiempoDeValidacionEnNanoSegundos);
                ValidadorDeOperacion.validarDefault(unaOperacion);
                //si no cumple una validacion la vuelvo a hacer hasta que
                // este marcada como que aprobo las validaciones
                System.out.println("Estoy loopeando");
                System.out.println("Termine de verificar");
            }
        };
        //hiloDeTarea.run();
        Timer cronometro = new Timer(true);
        cronometro.scheduleAtFixedRate(hiloDeTarea,0,tiempoDeValidacionEnMiliSegundos);
        eventos.add(hiloDeTarea);
    }

    public void ejecutar(long tiempoDeValidacionEnMiliSegundos,Egreso unaOperacion){
        this.ejecutar(tiempoDeValidacionEnMiliSegundos,0,unaOperacion);
    }

    public TimerTask proximoAEjecutar(){
        eventos.sort((TimerTask unEvento,TimerTask otroEvento) ->
                (int)(unEvento.scheduledExecutionTime() - otroEvento.scheduledExecutionTime()));
        return eventos.get(0);
    }
}

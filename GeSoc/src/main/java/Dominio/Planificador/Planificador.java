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

        TimerTask hiloDeTarea = new TimerTask() {
            @Override
            public void run() {
                ValidadorDeOperacion.validarDefault(unaOperacion);
                //si no cumple una validacion la vuelvo a hacer hasta que
                // este marcada como que aprobo las validaciones
                if(unaOperacion.isEstaVerificada()){
                    this.cancel();
                    eventos.remove(this);
                }
            }
        };
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

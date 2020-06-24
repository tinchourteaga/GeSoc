package Egreso.Validador.EstrategiasRevision;

import Egreso.Core.Egreso;
import Planificador.Planificador;
public class EjecucionAutomatica implements EstrategiaRevision {

    private long tiempoDeEsperaMili;
    private int tiempoDeEsperaNano;

    public EjecucionAutomatica(long tiempoDeEsperaMili,int tiempoDeEsperaNano){
        this.tiempoDeEsperaMili = tiempoDeEsperaMili;
        this.tiempoDeEsperaNano = tiempoDeEsperaNano;
    }
    @Override
    public void revisar(Egreso operacion) {
        Planificador planificador = Planificador.getPlanificador();
        planificador.ejecutar(tiempoDeEsperaMili,tiempoDeEsperaNano,operacion);
        //le paso la pelota al planificador en vez de pasarsela a la consola
    }

    public long getTiempoDeEsperaMili() {
        return tiempoDeEsperaMili;
    }

    public void setTiempoDeEsperaMili(long tiempoDeEsperaMili) {
        this.tiempoDeEsperaMili = tiempoDeEsperaMili;
    }

    public int getTiempoDeEsperaNano() {
        return tiempoDeEsperaNano;
    }

    public void setTiempoDeEsperaNano(int tiempoDeEsperaNano) {
        this.tiempoDeEsperaNano = tiempoDeEsperaNano;
    }
}

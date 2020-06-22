package Egreso.Validador;

import Egreso.Core.Egreso;
import Planificador.Planificador;
import Usuario.Usuario;

public class EjecucionAutomatica implements EstrategiaRevision {

    long tiempoDeEsperaMili;
    int tiempoDeEsperaNano;

    public EjecucionAutomatica(long tiempoDeEsperaMili,int tiempoDeEsperaNano){
        this.tiempoDeEsperaMili = tiempoDeEsperaMili;
        this.tiempoDeEsperaNano = tiempoDeEsperaNano;
    }
    @Override
    public void revisar(Egreso operacion, Usuario usuario) {
        Planificador planificador = Planificador.getPlanificador();
        planificador.ejecutar(tiempoDeEsperaMili,tiempoDeEsperaNano,operacion,usuario);
        //Aca hay que crear la bandeja y asociarsela al Rol
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

package Operacion.Validador;

import Operacion.Core.Operacion;
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
    public void revisar(Operacion operacion, Usuario usuario) {
        Planificador planificador = Planificador.getPlanificador();
        planificador.ejecutar(tiempoDeEsperaMili,tiempoDeEsperaNano,operacion,usuario);
        //Aca hay que crear la bandeja y asociarsela al Rol
    }
}

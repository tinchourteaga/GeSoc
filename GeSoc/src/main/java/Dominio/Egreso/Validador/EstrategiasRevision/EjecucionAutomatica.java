package Dominio.Egreso.Validador.EstrategiasRevision;

import Dominio.Egreso.Core.Egreso;
import Dominio.Planificador.Planificador;
import Dominio.Planificador.PlanificadorQuartz;
import org.quartz.SchedulerException;

public class EjecucionAutomatica implements EstrategiaRevision {

    private int horaEjecucion;
    private int minutoEjecucion;

    public EjecucionAutomatica(int horaEjecucion,int minutoEjecucion){
        this.horaEjecucion = horaEjecucion;
        this.minutoEjecucion = minutoEjecucion;
    }
    @Override
    public void revisar(Egreso operacion) {
        try {
            PlanificadorQuartz plani=PlanificadorQuartz.getPlanificador();
            plani.fireJob(horaEjecucion,minutoEjecucion,operacion);
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}

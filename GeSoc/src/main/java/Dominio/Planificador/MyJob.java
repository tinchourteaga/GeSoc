package Dominio.Planificador;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Egreso operacionAValidar=(Egreso) jobExecutionContext.getJobDetail().getJobDataMap().get("Operacion");
        ValidadorDeOperacion.validarDefault(operacionAValidar);
    }
}

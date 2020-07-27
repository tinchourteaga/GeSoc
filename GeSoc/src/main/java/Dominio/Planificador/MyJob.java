package Dominio.Planificador;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Ejecutando validación de egresos...");
        Egreso operacionAValidar=(Egreso) jobExecutionContext.getJobDetail().getJobDataMap().get("Operacion");
        System.out.println("Ejecutando validación de egresos2...");
        ValidadorDeOperacion.validarDefault(operacionAValidar);
        System.out.println(operacionAValidar.isEstaVerificada());
        System.out.println("Ejecutando validación de egresos3...");


    }
}

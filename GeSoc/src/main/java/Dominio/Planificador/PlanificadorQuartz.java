package Dominio.Planificador;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import org.quartz.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


public class PlanificadorQuartz {
    private CountDownLatch latch = new CountDownLatch(1);
    private static SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
    private static Scheduler scheduler;
    private static PlanificadorQuartz instancia = null;

    static {
        try {
            scheduler = schedFact.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private PlanificadorQuartz(){}

    public static PlanificadorQuartz getPlanificador() throws SchedulerException {
        if(instancia == null) {
            instancia = new PlanificadorQuartz();
            scheduler.start();
        }

        return instancia;
    }

    public void fireJob(int hour, int min, Egreso unEgreso) throws SchedulerException, InterruptedException {

        // define the job and tie it to our HelloJob class
        JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
        JobDataMap data = new JobDataMap();
        data.put("Operacion",unEgreso);
        data.put("latch", this);

        JobDetail jobDetail = jobBuilder.usingJobData("Descripcion:", "Validacion de la operacion")
                .usingJobData(data)
                .withIdentity("myJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startAt(DateBuilder.todayAt(hour, min, 0))
                .withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(jobDetail, trigger);
        latch.await();

    }

    public void countDown() {
        latch.countDown();
    }


}
package Persistencia;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Ingreso;

import javax.persistence.TypedQuery;
import java.util.List;

import static Persistencia.EntityManagerHelper.getEntityManager;

public class QueriesUtiles {

    public static List<Egreso> obtenerEgresosDe(String userName){

        String queryString = "SELECT e FROM Egreso e JOIN e.revisores us WHERE us.persona = :username ";

        TypedQuery<Egreso> query = getEntityManager().createQuery(queryString, Egreso.class);
        query.setParameter("username", userName);
        return query.getResultList();
    }
    public static List<Presupuesto> obtenerPresupuestosDe(Egreso egreso){

        String queryString = "SELECT p FROM Presupuesto p JOIN p.egreso eg WHERE eg.egreso = :egresoID ";
        TypedQuery<Presupuesto> query = getEntityManager().createQuery(queryString, Presupuesto.class);
        query.setParameter("egresoID", egreso.getEgreso());
        return query.getResultList();
    }
    public static List<Ingreso> obtenerTodosLosIngresosDe(Entidad unaEntidad){
        String queryString = "SELECT i FROM Ingreso i JOIN i.entidad ent WHERE ent.entidad = :entidadID ";
        TypedQuery<Ingreso> query = getEntityManager().createQuery(queryString, Ingreso.class);
        query.setParameter("entidadID", unaEntidad.getEntidad());
        return query.getResultList();
    }

}

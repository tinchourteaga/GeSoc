package Persistencia;

import Dominio.Egreso.Core.Egreso;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static Persistencia.EntityManagerHelper.getEntityManager;

public class QueriesUtiles {

    public static List<Egreso> obtenerEgresosDe(String userName){


        List<Egreso> egresos= new ArrayList();
        String queryString = "SELECT e FROM Egreso e JOIN e.revisores us WHERE us.persona = :username ";

        TypedQuery<Egreso> query = getEntityManager().createQuery(queryString, Egreso.class);
        query.setParameter("username", userName);
        return query.getResultList();
    }
}

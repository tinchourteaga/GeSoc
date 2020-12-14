package Persistencia;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.Detalle;
import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Ingreso;
import Dominio.Usuario.Usuario;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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

    public static List<Egreso> obtenerEgresosNoPactados(String nickName) {
        String queryString = "SELECT e FROM Egreso e JOIN e.revisores us WHERE us.persona = :username AND (e.presupuestoPactado is NULL )";
        TypedQuery<Egreso> query = getEntityManager().createQuery(queryString, Egreso.class);
        query.setParameter("username", nickName);
        return query.getResultList();
    }

    public static List<Egreso>  obtenerEgresosPactados(String nickName) {
        String queryString = "SELECT e FROM Egreso e JOIN e.revisores us WHERE us.persona = :username AND (e.presupuestoPactado != 'NULL'  )";
        TypedQuery<Egreso> query = getEntityManager().createQuery(queryString, Egreso.class);
        query.setParameter("username", nickName);
        query.getResultList().forEach(q->System.out.println(q));
        return query.getResultList();
    }

    public static Proveedor obtenerProveedorDe(Presupuesto presu) {
        String queryString = "SELECT prov FROM Presupuesto p JOIN p.proveedor prov WHERE p.presupuesto = :presuID ";
        TypedQuery<Proveedor> query = getEntityManager().createQuery(queryString, Proveedor.class);
        query.setParameter("presuID", presu.getPresupuesto());
        return query.getSingleResult();
    }

    public static List<Detalle> obtenerDetallesDe(Presupuesto presu) {
        String queryString = "SELECT det FROM Presupuesto p JOIN p.detalles det WHERE p.presupuesto = :presupuestoID ";
        TypedQuery<Detalle> query = getEntityManager().createQuery(queryString, Detalle.class);
        query.setParameter("presupuestoID", presu.getPresupuesto());
        return query.getResultList();
    }

    public static Optional<Egreso> obtenerEgresosPactadoDeParaPresupuesto(Presupuesto presu, String nickName) {
        //esto habria que testearlo TODO
        String queryString = "SELECT eg FROM Egreso eg JOIN eg.presupuestoPactado pres JOIN eg.revisores revisores " +
                "WHERE pres.presupuesto = :presupuestoID AND revisores.persona = :userName ";
        TypedQuery<Egreso> query = getEntityManager().createQuery(queryString, Egreso.class);
        query.setParameter("presupuestoID", presu.getPresupuesto());
        query.setParameter("userName", nickName);
        return query.getResultList().stream().findFirst();

    }

    public static List<CategoriaCriterio> obtenerCategoriaDe(Presupuesto pre) {
        String queryString = "SELECT cat FROM Presupuesto presu JOIN presu.categorias cat WHERE presu.presupuesto = :presupuestoID";
        TypedQuery<CategoriaCriterio> query = getEntityManager().createQuery(queryString, CategoriaCriterio.class);
        query.setParameter("presupuestoID", pre.getPresupuesto());
        return query.getResultList();
    }

    public static CategoriaCriterio obtenerCategoriaPorPK(Integer categoriaID) {
        String queryString = "SELECT cat FROM CategoriaCriterio cat WHERE cat.categoria = :categoriaID";
        TypedQuery<CategoriaCriterio> query = getEntityManager().createQuery(queryString, CategoriaCriterio.class);
        query.setParameter("categoriaID", categoriaID);
        return query.getSingleResult();
    }

    public static Entidad obtenerEntidadDe(Egreso eg) {
        String queryString = "SELECT ent FROM Egreso eg JOIN eg.entidad ent WHERE eg.egreso = :egresoID";
        TypedQuery<Entidad> query = getEntityManager().createQuery(queryString, Entidad.class);
        query.setParameter("egresoID", eg.getEgreso());
        return query.getSingleResult();
    }

    public static List<Entidad> obtenerEntidadDeUsuario(Usuario us) {
        String queryString = "SELECT ent FROM Usuario us JOIN us.entidades ent WHERE us.usuario = :usuarioID";
        TypedQuery<Entidad> query = getEntityManager().createQuery(queryString, Entidad.class);
        query.setParameter("usuarioID", us.getUsuario());
        return query.getResultList();
    }

    public static List<CategoriaCriterio> obtenerCategoriasDe(Egreso eg) {
        String queryString = "SELECT cat FROM Egreso egresito JOIN egresito.categorias cat WHERE egresito.egreso = :egresoID";
        TypedQuery<CategoriaCriterio> query = getEntityManager().createQuery(queryString, CategoriaCriterio.class);
        query.setParameter("egresoID", eg.getEgreso());
        return query.getResultList();
    }
    public static List<CategoriaCriterio> obtenerCategoriasDe(Entidad ent) {
        String queryString = "SELECT cat FROM Entidad ent JOIN ent.criterios cri JOIN cri.categoriaCriterios cat WHERE ent.entidad = :entidadID";
        TypedQuery<CategoriaCriterio> query = getEntityManager().createQuery(queryString, CategoriaCriterio.class);
        query.setParameter("entidadID", ent.getEntidad());
        return query.getResultList();
    }


    public static List<Criterio> obtenerCriterioDe(Entidad ent) {
        String queryString = "SELECT crit FROM Entidad entidad JOIN entidad.criterios crit WHERE entidad.entidad = :entidadID";
        TypedQuery<Criterio> query = getEntityManager().createQuery(queryString, Criterio.class);
        query.setParameter("entidadID", ent.getEntidad());
        return query.getResultList();
    }

    public static List<Egreso> obtenerEgresosDeIngreso(Ingreso ing) {
        String queryString = "SELECT egresosGastados FROM Ingreso ingresito JOIN ingresito.gastadoEn egresosGastados WHERE ingresito.ingreso = :ingresoID";
        TypedQuery<Egreso> query = getEntityManager().createQuery(queryString, Egreso.class);
        query.setParameter("ingresoID", ing.getIngreso());
        return query.getResultList();
    }
}

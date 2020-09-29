package TestsEntrega4;

import Dominio.Egreso.Core.DocumentoComercial;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Core.TipoDocumentoComercial;
import Dominio.Entidad.Direccion;
import Dominio.Entidad.DireccionPostal;
import Dominio.Moneda.Valor;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;
import Persistencia.EntityManagerHelper;

public class Test{
    //ControllerMercadoLibre varController = ControllerMercadoLibre.getControllerMercadoLibre();

    @org.junit.Test
    public void persistirProveedor(){
        Pais pais = new Pais("AR", "Argentina");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(pais);
        EntityManagerHelper.commit();

        Pais argentina = (Pais) EntityManagerHelper.createQuery("from Pais where nombre = 'Argentina'").getSingleResult();

        Provincia provincia = new Provincia("BSAS", "Buenos Aires", argentina);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(provincia);
        EntityManagerHelper.commit();

        Provincia bsas = (Provincia) EntityManagerHelper.createQuery("from Provincia where nombre = 'Buenos Aires'").getSingleResult();

        Ciudad ciudad = new Ciudad("A", "Adrogué", bsas);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(ciudad);
        EntityManagerHelper.commit();

        Ciudad adrogue = (Ciudad) EntityManagerHelper.createQuery("from Ciudad where nombre = 'Adrogué'").getSingleResult();

        Direccion dir = new Direccion("Larralde", "2454", "3", "A");

        DireccionPostal dirPost = new DireccionPostal(dir, 1850, argentina, bsas, adrogue);

        Proveedor prov = new Proveedor("Razon Social", "28-49367281-6", dirPost);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(prov);
        EntityManagerHelper.commit();
    }

    @org.junit.Test
    public void persistirDocumentoComercial(){
        DocumentoComercial doc = new DocumentoComercial(TipoDocumentoComercial.REMITO, "N° 00003500");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(doc);
        EntityManagerHelper.commit();
    }

    @org.junit.Test
    public void persistirValor(){
        Valor valor = new Valor("Argentina",1285);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(valor);
        EntityManagerHelper.commit();
    }

    /* @org.junit.Test
    public void persistirPresupuesto(){
        persistirProveedor();
        Proveedor proveedor = (Proveedor) EntityManagerHelper.createQuery("from Proveedor where razonSocial = 'Razon Social'").getSingleResult();

        persistirDocumentoComercial();
        DocumentoComercial doc = (DocumentoComercial) EntityManagerHelper.createQuery("from DocumentoComercial where tipo = 'REMITO'").getSingleResult();

        Presupuesto presupuesto = new Presupuesto(new ArrayList<>(),51000, new ArrayList<>(), doc, proveedor);
        presupuesto.setFecha(LocalDate.of(1998, 8, 9));

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(presupuesto);
        EntityManagerHelper.commit();

        // Este no funciona por la fecha, pero no entiendo porqué
    } */
}

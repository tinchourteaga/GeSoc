package TestsEntrega4;

import Lugares.Pais;
import Persistencia.EntityManagerHelper;

public class Test {
    @org.junit.Test
    public void persistirPais() {
        Pais pais = new Pais("AR", "Argentina");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(pais);
        EntityManagerHelper.commit();
    }
}

package TestsEntrega3.CriterioDummy;

import Egreso.Core.CriteriosDeCategorizacion.Categoria;
import Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Egreso.Core.Egreso;

public class CriterioDummy extends Criterio {
    @Override
    public void aplicar(Egreso unaEgreso) {
        unaEgreso.getCategorias().add(new Categoria("no hago nada"+unaEgreso.getCategorias().size(),"dummy"));
    }
}

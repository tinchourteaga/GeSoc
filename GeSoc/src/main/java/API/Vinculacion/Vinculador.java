package API.Vinculacion;

import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Ingreso;

import java.io.IOException;
import java.util.List;

public interface Vinculador {
    void vincular(List<Egreso> egresos, List<Ingreso> ingresos,List<String> criterios, List<Condicion> condiciones) throws IOException;
}

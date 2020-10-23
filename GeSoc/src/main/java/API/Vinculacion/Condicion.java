package API.Vinculacion;

import java.util.ArrayList;
import java.util.List;

public class Condicion {

    String nombreCondicion;
    List<Object> parametros=new ArrayList();

    public Condicion(String nombreCondicion, List<Object> parametros) {
        this.nombreCondicion = nombreCondicion;
        this.parametros = parametros;
    }
}

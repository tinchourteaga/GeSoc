package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Entidad.Entidad;

import java.util.ArrayList;
import java.util.List;

public class CriterioCompuesto extends Criterio {
    List<Criterio> criteriosHijos= new ArrayList<>();
    @Override
    public void aplicar(Entidad unaEntidad) {
        criteriosHijos.forEach(hijo->hijo.aplicar(unaEntidad));
    }
}

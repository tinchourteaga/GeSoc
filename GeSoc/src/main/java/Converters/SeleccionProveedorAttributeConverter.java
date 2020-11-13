package Converters;

import Dominio.Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class SeleccionProveedorAttributeConverter implements AttributeConverter<CriterioSeleccionProveedor, String> {
    @Override
    public String convertToDatabaseColumn(CriterioSeleccionProveedor crit) {
        return crit.getClass().getSimpleName();
    }

    @Override
    public CriterioSeleccionProveedor convertToEntityAttribute(String className) {

    CriterioSeleccionProveedor criterio=null;

    if(className=="CriterioMenorPrecio"){
        criterio=new CriterioMenorPrecio();
    }
    return criterio;
    }
}

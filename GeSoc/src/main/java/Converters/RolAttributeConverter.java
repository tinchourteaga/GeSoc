package Converters;

import Dominio.Rol.Rol;
import Dominio.Rol.RolAdministrador;
import Dominio.Rol.RolEstandar;
import Dominio.Rol.RolRevisorCompra;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RolAttributeConverter implements AttributeConverter<Rol, String> {

    @Override
    public String convertToDatabaseColumn(Rol rol) {
        return rol.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public Rol convertToEntityAttribute(String data) {
        switch(data) {
            case "estandar":
                return new RolEstandar();
            case "administrador":
                return new RolAdministrador();
            case "revisor":
                return new RolRevisorCompra();
            default:
                return null;
        }
    }
}
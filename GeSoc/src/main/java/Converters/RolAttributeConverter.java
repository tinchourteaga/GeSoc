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
        String className=rol.getClass().getSimpleName().toLowerCase();
        if(className.equals("revisor")){
            insertarEgresosXUsuario(rol);
        }
        return className;
    }

    private void insertarEgresosXUsuario(Rol rol) {
    }

    @Override
    public Rol convertToEntityAttribute(String data) {
        switch(data) {
            case "estandar":
                return new RolEstandar();
            case "administrador":
                return new RolAdministrador();
            case "revisor":
                RolRevisorCompra unRevisor=new RolRevisorCompra();
                agregarEgresosAsociados(unRevisor);
                return unRevisor;
            default:
                return null;
        }
    }

    private void agregarEgresosAsociados(RolRevisorCompra unRevisor) {

        //no puedo usar criteria porque no se como trabajar con la tabla autogenerada RevisorXEgreso
   /*     EntityManager entityManager = EntityManagerSingleton.get();
        String idUsuario=obtenerIDUsuario();
        String stringConsulta = "SELECT e FROM Egreso e JOIN e.usuario p WHERE p.id="+idUsuario;

        TypedQuery<Egreso> query = entityManager.createQuery(stringConsulta, Egreso.class);

        List<Egreso> listaEgresos = query.getResultList();
        listaEgresos.forEach(egreso->unRevisor.agregarEgreso(egreso));
    }

    private String obtenerIDUsuario() {
        return "";*/
    }
}
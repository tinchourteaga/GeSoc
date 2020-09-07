package API.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

public class CiudadDTO implements IdPersistedClass, NamePersistedClass {
    private String id;
    private String name;

    public CiudadDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getNombre() {
        return name;
    }
}

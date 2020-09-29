package API.ML.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

import java.util.ArrayList;
import java.util.List;

public class ProvinciaDTO  implements IdPersistedClass, NamePersistedClass {
        String id;
        String name;

        private List<CiudadDTO> ciudades = new ArrayList<>();

        public ProvinciaDTO (String id, String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String getId() { return id; }
        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public List<CiudadDTO> getCiudades() { return ciudades; }
        @Override
        public String getNombre() {
        return name;
    }
}

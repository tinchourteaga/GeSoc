package API.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

public class ProvinciaDTO  implements IdPersistedClass, NamePersistedClass {
        String id;
        String name;

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

    @Override
    public String getNombre() {
        return name;
    }
}

package API.DTOs;

import java.util.List;

public class ProvinciaDTO {
        String id;
        String name;
        List<CiudadDTO> ciudades;

        public ProvinciaDTO (String id, String name){
            this.id = id;
            this.name = name;
        }

        public List<CiudadDTO> getCiudades(){return ciudades;}
        public void agregarCiudad(CiudadDTO unaCiudad){ciudades.add(unaCiudad);}
        public void removerCiudad(CiudadDTO unaCiudad){ciudades.remove(unaCiudad);}
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
}

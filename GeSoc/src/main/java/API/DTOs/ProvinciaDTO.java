package API.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "geo_provincias")
public class ProvinciaDTO  implements IdPersistedClass, NamePersistedClass {
    @Id
    @GeneratedValue
    private int provincia;

    @Transient
    String id;

    @Column(name = "nombre")
    String name;

    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "pais")
    private PaisDTO pais;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<CiudadDTO> ciudades;

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

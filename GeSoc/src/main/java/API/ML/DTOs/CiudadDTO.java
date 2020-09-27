package API.ML.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

import javax.persistence.*;

@Entity
@Table(name = "geo_ciudades")
public class CiudadDTO implements IdPersistedClass, NamePersistedClass {
    @Id
    @GeneratedValue
    private int ciudad;

    @Transient
    private String id;

    @Column(name = "nombre")
    private String name;

    @ManyToOne
    @JoinColumn(name = "provincia", referencedColumnName = "provincia")
    private ProvinciaDTO provincia;

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

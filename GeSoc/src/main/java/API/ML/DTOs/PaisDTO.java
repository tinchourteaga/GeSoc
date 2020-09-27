package API.ML.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Entity
@Table(name = "geo_paises")
public class PaisDTO  implements IdPersistedClass, NamePersistedClass {
    @Id
    @GeneratedValue
    private int pais;

    @Transient
    private String id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<ProvinciaDTO> provincias;

    @Transient
    private String locale;

    @Transient
    private String currency_id;

    public PaisDTO(String id, String name, String locale, String currency_id) throws IOException {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.currency_id = currency_id;
    }

    @Override
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocale() {
        return locale;
    }
    public String getCurrency_id() {
        return currency_id;
    }

    @Override
    public String getNombre() {
        return name;
    }
}

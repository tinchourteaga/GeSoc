package Lugares;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "geo_provincias"/*, uniqueConstraints = @UniqueConstraint(columnNames = {"provincia", "pais"})*/)
public class Provincia {
    @Id
    @GeneratedValue
    private int provincia;

    @Transient
    String id;

    @Column(name = "nombre")
    String name;

    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "pais")
    Pais pais;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    List<Ciudad> ciudades = new ArrayList<>();;

    public Provincia(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Provincia(String id, String name, Pais pais){
        this.id = id;
        this.name = name;
        this.pais = pais;
    }

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
    public List<Ciudad> getCiudades() { return ciudades; }
}

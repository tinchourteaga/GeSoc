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

    @Column(name="id_mercado_libre")
    String id;

    @Column(name = "nombre")
    String name;

    @ManyToOne
    @JoinColumn(name = "pais")
    Pais pais;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "provincia")
    List<Ciudad> ciudades = new ArrayList<>();


    public Provincia() { }

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

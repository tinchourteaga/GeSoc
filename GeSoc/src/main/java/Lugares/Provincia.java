package Lugares;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "geo_provincias")
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

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    List<Ciudad> ciudades;

    public Provincia (String id, String name){
        this.id = id;
        this.name = name;
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
}

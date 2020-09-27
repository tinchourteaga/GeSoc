package Lugares;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "geo_paises")
public class Pais {
    @Id
    @GeneratedValue
    private int pais;

    @Transient
    String id;

    @Column(name = "nombre")
    String name;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Provincia> provincias;

    public Pais (String id, String name){
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
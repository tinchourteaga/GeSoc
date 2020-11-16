package Lugares;

import javax.persistence.*;

@Entity
@Table(name = "geo_ciudades"/*, uniqueConstraints = @UniqueConstraint(columnNames = {"ciudad", "provincia"})*/)
public class Ciudad {
    @Id
    @GeneratedValue
    private int ciudad;

    @Transient
    String id;

    @Column(name = "nombre")
    String name;

    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;

    public Ciudad() { }

    public Ciudad(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Ciudad(String id, String name, Provincia provincia){
        this.id = id;
        this.name = name;
        this.provincia = provincia;
    }

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public int getCiudad(){ return ciudad; }
}

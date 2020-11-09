package Dominio.Egreso.Core;

import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Entidad.DireccionPostal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pers_proveedores")
public class Proveedor {
    @Id
    @GeneratedValue
    private int proveedor;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nro_documento")
    private String documento;

    @Embedded
    private DireccionPostal direccion;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    public Proveedor() { }

    public Proveedor(String nombre, String apellido, String dni, DireccionPostal unaDireccion) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.documento=dni;
        this.direccion=unaDireccion;
        this.presupuestos= new ArrayList<Presupuesto>();
    }

    public Proveedor(String rs, String cuitOcuil, DireccionPostal unaDireccion) {
        this.razonSocial=rs;
        this.documento=cuitOcuil;
        this.direccion=unaDireccion;
        this.presupuestos= new ArrayList<Presupuesto>();
    }

    public String getRazonSocial() {
        return razonSocial;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDocumento() {
        return documento;
    }
    public DireccionPostal getDireccion() {
        return direccion;
    }
    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }
    public Presupuesto getPresupuestoCriterio(CriterioSeleccionProveedor criterio){ return criterio.seleccionarPresupuesto(presupuestos); }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido= apellido; }
    public void setDireccion(DireccionPostal direccion) { this.direccion = direccion; }
}

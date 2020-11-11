package Dominio.Entidad;

import Dominio.Entidad.Categorias.Categoria;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends TipoEntidadJuridica {

    @ManyToOne
    @JoinColumn(name = "categoria",insertable = false, updatable = false)
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void determinarCategoria() {

        List<Categoria> categorias = this.getActividad().getCategorias().
                stream().filter(unaCategoria ->unaCategoria.getVentasAnuales() >= this.getPromedioVentasAnuales()
                || unaCategoria.getPersonalMaximo() >= this.getCantidadPersonal()).collect(Collectors.toList());

        this.setCategoria(categorias.stream().max(Comparator.comparingInt(unaCategoria ->
                unaCategoria.getPersonalMaximo())).get());
    }
}

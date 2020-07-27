package Dominio.Entidad;

import Dominio.Entidad.Categorias.Categoria;

public class Empresa extends TipoEntidadJuridica {

    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

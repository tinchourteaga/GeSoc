package Dominio.Egreso.Entidad;

import Dominio.Egreso.Entidad.Categorias.Categoria;

public class Empresa extends EntidadJuridica {

    private Categoria categoria;

    public Empresa(String nombreEntidad, String descripcionEntidad, String rs,String cuitEntidad,String ci){
        super(nombreEntidad,descripcionEntidad, rs,cuitEntidad,ci);
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

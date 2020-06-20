package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Core.Egreso;

public class Categoria {
    //El Usuario va a poder crear sus propias categorias
   String descripicion;
   String nombreDeCategoria;

    public Categoria(String desc, String nombreDeCategoria) {
        this.descripicion=desc;
        this.nombreDeCategoria=nombreDeCategoria;
    }
    public String getDescripicion() {
        return descripicion;
    }
    public String getNombreDeCategoria() {
        return nombreDeCategoria;
    }
    public void cambiarDescripicion(String Nuevadescripicion) {
        this.descripicion = descripicion;
    }
    public void CambiarNombreCategoria(String nuevoNombreDeCategoria) {
        this.nombreDeCategoria = nombreDeCategoria;
    }
}

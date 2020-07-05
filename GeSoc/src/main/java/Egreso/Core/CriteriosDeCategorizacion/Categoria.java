package Egreso.Core.CriteriosDeCategorizacion;

public class Categoria {
    //El Usuario va a poder crear sus propias categorias
   private String descripicion;
   private String nombreDeCategoria;

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

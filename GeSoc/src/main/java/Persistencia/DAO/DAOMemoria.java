package Persistencia.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DAOMemoria <T> implements DAO  {

    private List<Object> listaElementos;

    public DAOMemoria(){
        listaElementos = new ArrayList<>();
    }

    public <T> void agregar(T elemento) {
        if (this.existe(elemento))
            listaElementos.add(elemento);
    }

    public <T> void modificar(T elememto, T elementoModificado) {
        int indiceUsuario;
        if (this.existe(elememto)){
            indiceUsuario=buscar(elememto);
            listaElementos.add(indiceUsuario, elementoModificado);
        }
    }

    public <T> void eliminar(T elemento) {
        listaElementos.remove(elemento);
    }

    public <T> boolean existe(T elemento) {
        if (this.buscar(elemento) != -1){
            return true;
        }else{
            return false;
        }
    }

    public <T> int buscar(T elemento) {
        return listaElementos.indexOf(elemento);
    }

    @Override
    public  Object buscarPorId(String id) {
        return this.getAllElementos().stream().filter(obj ->((IdClass) obj).getId().equals(id));
    }

    @Override
    public  Object buscarPorNombre(String nombre) {
        return this.getAllElementos().stream().filter(obj ->((NameClass) obj).getNombre().equals(nombre));
    }

    @Override
    public List<Object> getAllElementos() {
        return listaElementos;
    }

    @Override
    public List<Object> getAllElementosFrom(Class unaClase){
        return listaElementos.stream().filter(objeto -> objeto.getClass().equals(unaClase)).collect(Collectors.toList());
    }

    public class NameClass{
        private String nombre;

        public String getNombre() {
            return nombre;
        }
    }

    public class IdClass{
        private String id;

        public String getId() {
            return id;
        }
    }
}

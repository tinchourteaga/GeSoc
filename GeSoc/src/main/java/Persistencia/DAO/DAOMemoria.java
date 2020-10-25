package Persistencia.DAO;

import Lugares.Pais;
import Persistencia.InterfacesPersistencia.IdPersistedClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DAOMemoria <T> implements DAO  {

    private List<Object> listaElementos = new ArrayList<>();;

    public <T> void agregar(T elemento) {
        if (!this.existe(elemento))
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

        List<Object> candidatos=this.getAllElementos().stream().filter(obj ->((IdPersistedClass) obj).getId().equals(id)).collect(Collectors.toList());
        if(candidatos!=null){
            return candidatos.get(0);
        }
        return null;
    }

    @Override
    public Object buscarPorPK(int id) { return null; }

    @Override
    public Object buscarPorNombre(String nombre) {
        List<Object> candidatos=this.getAllElementos().stream().filter(obj ->((Pais) obj).getName().equals(nombre)).collect(Collectors.toList());
        if(candidatos!=null){
            return candidatos.get(0);
        }
        return null;
    }

    @Override
    public List<Object> getAllElementos() {
        return listaElementos;
    }

    @Override
    public List<Object> getAllElementosFrom(Class unaClase){
        return listaElementos.stream().filter(objeto -> objeto.getClass().equals(unaClase)).collect(Collectors.toList());
    }


}

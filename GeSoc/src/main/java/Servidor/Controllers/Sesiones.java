package Servidor.Controllers;

import Dominio.Usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Sesiones {

    private Map<String,String> sesionesActuales=new HashMap<String,String>();

    static private Sesiones instance=null;

    static public Sesiones getInstance(){
        if(instance == null){
            instance=new Sesiones();
        }
        return instance;
    }
    private Sesiones(){}

    public void agregarSesion(String usuario,String token){
        sesionesActuales.put(usuario,token);
    }

    public void caducarSesion(String usuario){
        sesionesActuales.remove(usuario);
    }

    public boolean existeToken(String token){
        return sesionesActuales.containsKey(token);
    }

    public boolean estaLogueadoUsuario(String usuario){
        return sesionesActuales.containsValue(usuario);
    }

}

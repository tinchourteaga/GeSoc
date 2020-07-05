package Rol.Acciones;

import BandejaMensajes.Mensaje;

public class LeerMensaje implements Accion {
    private Mensaje mensajeAsociado;

    public LeerMensaje(Mensaje mensajeAsociado) {
        this.mensajeAsociado=mensajeAsociado;
    }

    @Override
    public void realizar() {
        //probablemente haya que hacer algo con esto despues con la capa presentacion
        mensajeAsociado.leer();
    }
}

package Dominio.Egreso.Validador;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.EstrategiasRevision.EstrategiaRevision;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Egreso.Validador.Validaciones.ValidacionOperacion;
import Dominio.Rol.Acciones.LeerMensaje;
import Dominio.Rol.Acciones.RevisarBandeja;
import Dominio.Rol.Mensajero;
import Dominio.Rol.RolRevisorCompra;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOMemoria;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ValidadorDeOperacion {

    private static DAO repositorio = new DAOMemoria<>();
    private static List<ValidacionOperacion> validaciones = repositorio.getAllElementos();
    private static EstrategiaRevision estrategia;

    public static DAO getRepositorio() {
        return repositorio;
    }
    public static List<ValidacionOperacion> getValidaciones() {
        return validaciones;
    }
    public static EstrategiaRevision getEstrategia() {
        return estrategia;
    }
    public static void setEstrategia(EstrategiaRevision nuevaEstrategia) {
        ValidadorDeOperacion.estrategia = nuevaEstrategia;
    }
    public static void validarPorEstrategia(Egreso egreso){
        estrategia.revisar(egreso);
    }
    public static Mensaje validarCustomSinBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas) {
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(new Date(), null, "Paso exitosamente todas las Validaciones"));

        AtomicBoolean flag = new AtomicBoolean(true);

        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
                //unaOperacion.setEstaVerificada(true);
            } catch (NoCumpleValidacionException | NoCumpleValidacionDeCriterioException e) {
                mensaje.set(new Mensaje(new Date(), null, e.toString()));
                unaOperacion.setEstaVerificada(false);
                flag.set(false);
            }
        });
        unaOperacion.setEstaVerificada(flag.get());
        return mensaje.get();
    }

    public static void validarCustomConBasicas(Egreso unaOperacion, List<ValidacionOperacion> validacionesEspecificas, Usuario unUsuario) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validarDefault(unaOperacion);
        validarCustomSinBasicas(unaOperacion, validacionesEspecificas);
    }

    public static void validarDefault(Egreso unaOperacion){
        Mensaje mensaje= validarCustomSinBasicas(unaOperacion,validaciones);
        List<RolRevisorCompra> revisores=Mensajero.obtenerRevisoresDe(unaOperacion);
        revisores.forEach(rol->enviarMensaje(rol,mensaje));
    }

    private static void enviarMensaje(RolRevisorCompra rol, Mensaje mensaje) {
       RevisarBandeja accionRevisor= (RevisarBandeja) rol.getAcciones().stream().filter(accion -> accion.getClass().equals(RevisarBandeja.class)).collect(Collectors.toList()).get(0);
       rol.getAcciones().add(new LeerMensaje(mensaje));
       accionRevisor.getBandejaAsociada().agregarMensaje(mensaje);
    }

}
package Dominio.Egreso.Validador;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.EstrategiasRevision.EstrategiaRevision;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Egreso.Validador.Validaciones.ValidacionOperacion;
import Dominio.Rol.Mensajero;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOMemoria;
import Persistencia.Repos.Repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Mensaje> mensaje = new AtomicReference<Mensaje>(new Mensaje(LocalDate.now(), null, "Paso exitosamente todas las Validaciones", unaOperacion));

        AtomicBoolean flag = new AtomicBoolean(true);

        validacionesEspecificas.forEach(validacion -> {
            try {
                validacion.validar(unaOperacion);
            } catch (NoCumpleValidacionException e) {
                mensaje.set(new Mensaje(LocalDate.now(), null,"La operacion realizada en fecha " + unaOperacion.getFecha() + " y con valor: " +unaOperacion.getValor().getImporte() +" no se valido correctamente. ya que no cumple con las validaciones requeridas" ,unaOperacion));
                unaOperacion.setEstaVerificada(false);
                flag.set(false);
            } catch (NoCumpleValidacionDeCriterioException e) {
                mensaje.set(new Mensaje(LocalDate.now(), null, "La operacion realizada en fecha " + unaOperacion.getFecha() + " y con valor: " +unaOperacion.getValor().getImporte() +" no se valido correctamente ya que no cumple con los criterios propuestos. Debe validarse nuevamente",unaOperacion));
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
        List<Usuario> revisores=Mensajero.obtenerRevisoresDe(unaOperacion);
        revisores.forEach(rol->enviarMensaje(rol,mensaje));
    }

    private static void enviarMensaje(Usuario usuario, Mensaje mensaje) {
       usuario.getBandejaDeMensajes().agregarMensaje(mensaje);
       mensaje.setUsuario(usuario);
       Repositorio repoMsj= new Repositorio(new DAOBBDD<Mensaje>(Mensaje.class));
       repoMsj.agregar(mensaje);
        Repositorio repoUs= new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
        repoUs.modificar(null,usuario);
    }

}
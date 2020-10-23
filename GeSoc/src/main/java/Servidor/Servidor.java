package Servidor;

import Servidor.Controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Servidor {

    //Preguntar si es necesario poner un repo para mantener sesiones
    static HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    public static void levantarServidor(){

        enableDebugScreen();
        port(4575);

        boolean localhost = true;
        if (localhost) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/templates/";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }

        //repositorio.inicializarRepo();

        // acceso: http://localhost:4575/
        levantarRutas();
    }

    public static void levantarRutas(){

        levantarRutaGET();
        levantarRutaPOST();
        levantarRutaDELETE();
        levantarRutaPUT();
        levantarRutaPATCH();
    }

    public static void levantarRutaGET(){
        Spark.get("/", ControllerSesion::mostrarLogin, engine);
        Spark.get("/cerrarSesion", ControllerSesion::cerrarSesion, engine);
        Spark.get("/autenticacion_usuario", ControllerAutenticacion::visualizarPantalla, engine);
        Spark.get("/cargar_egreso", ControllerEgresos::visualizarPantalla, engine);
        Spark.get("/cargar_entidad", ControllerEntidad::visualizarPantalla, engine);
        Spark.get("/cargar_ingreso", ControllerIngresos::visualizarPantalla, engine);
        Spark.get("/cargar_presupuesto", ControllerPresupuesto::visualizarPantalla, engine);
        Spark.get("/cargar_proveedor", ControllerProveedor::visualizarPantalla, engine);
        Spark.get("/crear_criterio", ControllerCriterio::visualizarPantalla, engine);
        Spark.get("/crear_categoria", ControllerCategoria::visualizarPantalla, engine);
        Spark.get("/crear_proyecto_de_financiamiento", ControllerProyectoFinanciamiento::visualizarPantalla, engine);
        Spark.get("/datos_usuario", ControllerUsuario::visualizarPantallaDatosUsuario, engine);
        Spark.get("/usuario", ControllerUsuario::visualizarPantallaUsuario, engine);
        Spark.get("/pantalla_principal_usuario", ControllerUsuario::visualizarPantallaPrincipalUsuario, engine);
        Spark.get("/administrar_usuarios", ControllerUsuario::visualizarPantallaAdministrarUsuario, engine);
        Spark.get("/configuracion_general", ControllerUsuario::visualizarPantallaConfiguracionGeneral, engine);
        Spark.get("/bitacora_operaciones", ControllerBitacora::visualizarPantalla, engine);
        Spark.get("/asociar_egresos_y_presupuestos", ControllerAsociacion::visualizarPantallaEgresosYPresupuestos, engine);
        Spark.get("/asociar_egresos_e_ingresos", ControllerAsociacion::visualizarPantallaIngresosYEgresos, engine);
        Spark.get("/mensajes_no_revisor", ControllerMensajes::visualizarPantallaMensajesNoRevisor, engine);
        Spark.get("/mensajes_revisor", ControllerMensajes::visualizarPantallaMensajesRevisor, engine);
        Spark.get("/ver_egresos_e_ingresos", ControllerVisualizacionEI::visualizarPantalla, engine);



    }

    public static void levantarRutaPOST(){
        Spark.post("/validarLogin", ControllerSesion::validarLogin);
        Spark.post("/cargarEntidad", ControllerEntidad::cargarEntidad);
    }

    public static void levantarRutaDELETE(){

    }

    public static void levantarRutaPUT(){

    }

    public static void levantarRutaPATCH(){

    }
}

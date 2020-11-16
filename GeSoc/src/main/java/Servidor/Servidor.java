package Servidor;

import Servidor.Controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static Servidor.Controllers.Autenticador.authenticate;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Servidor {

    //Preguntar si es necesario poner un repo para mantener sesiones
    static HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    public static void levantarServidor(){

        enableDebugScreen();
        Integer puerto = 15003;
        port(puerto);

        boolean localhost = true;
        String projectDir = System.getProperty("user.dir");
        if (localhost) {

            String staticDir = "/src/main/resources/templates";
            System.out.println(projectDir + staticDir);
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            Spark.staticFiles.location("/templates");
        }

        //repositorio.inicializarRepo();

        // acceso: http://localhost:15002/
        System.out.println("Para correrlo local --> http://localhost:"+puerto+"/");
        System.out.println("Para correrlo en AWS --> http://18.218.220.180:"+puerto+"/");
        levantarRutas();
    }

    public static void levantarRutas(){

        levantarRutaGET();
        levantarRutaPOST();
        levantarRutaDELETE();
        levantarRutaPUT();
        levantarRutaPATCH();
        before("/*", authenticate);
        //esto ultimo es para que realice siempre la autenticación
    }

    public static void levantarRutaGET(){
        Spark.get("/", ControllerSesion::mostrarLogin, engine);
        Spark.get("/autenticacion_usuario", ControllerAutenticacion::visualizarPantalla, engine);
        Spark.get("/cargar_egreso", ControllerEgresos::visualizarPantalla, engine);
        Spark.get("/cargar_presupuesto", ControllerPresupuesto::visualizarPantalla, engine);
        Spark.get("/asociar_egresos_y_presupuestos", ControllerAsociacion::visualizarPantallaEgresosYPresupuestos, engine);
        Spark.get("/asociar_ingresos_y_egresos", ControllerAsociacion::visualizarPantallaIngresosYEgresos, engine);
        Spark.get("/cargar_entidad", ControllerEntidad::visualizarPantalla, engine);
        Spark.get("/cargar_ingreso", ControllerIngresos::visualizarPantalla, engine);
        Spark.get("/crear_categoria", ControllerCategoria::visualizarPantalla, engine);
        Spark.get("/pantalla_principal_usuario", ControllerUsuario::visualizarPantallaPrincipalUsuario, engine);
        Spark.get("/datos_usuario", ControllerUsuario::visualizarPantallaDatosUsuario, engine);//falta testear peor no hay boton para entrar jeje
        Spark.get("/mensajes", ControllerMensajes::visualizarPantallaMensajes, engine);
        Spark.get("/administrar_usuarios", ControllerUsuario::visualizarPantallaAdministrarUsuario, engine);//falta boton "Filtrar"
        Spark.get("/cargar_proveedor", ControllerProveedor::visualizarPantalla, engine);//Falta que se bloqueen bien los campos
        Spark.get("/crear_criterio", ControllerCriterio::visualizarPantalla, engine);//estaria bueno tener otro select pero de los criterios que pueden ser padre
        Spark.get("/validar_egresos", ControllerEgresos::visualizarPantallaValidacion, engine);//estaria bueno tener otro select pero de los criterios que pueden ser padre
        Spark.get("/validar_egreso_manualmente", ControllerEgresos::visualizarPantallaValidacionManual, engine);//estaria bueno tener otro select pero de los criterios que pueden ser padre


        //haciendo
        Spark.get("/ver_ingresos_y_egresos", ControllerVisualizacionEI::visualizarPantalla, engine);


        //para tincho
        Spark.get("/cargar_items_egreso", ControllerEgresos::visualizarPantallaItems, engine);
        Spark.get("/cargar_items_presupuestos", ControllerPresupuesto::visualizarPantallaItems, engine);

        //TODO



        //bloqueado
       Spark.get("/usuario", ControllerUsuario::visualizarPantallaUsuario, engine);


        //No son de la entrega 6
        Spark.get("/crear_proyecto_de_financiamiento", ControllerProyectoFinanciamiento::visualizarPantalla, engine);
        Spark.get("/configuracion_general", ControllerUsuario::visualizarPantallaConfiguracionGeneral, engine);
        Spark.get("/bitacora_operaciones", ControllerBitacora::visualizarPantalla, engine);


    }

    public static void levantarRutaPOST(){
        //done
        Spark.post("/validarLogin", ControllerSesion::validarLogin);
        Spark.post("/cerrarSesion", ControllerSesion::cerrarSesion);
        Spark.post("/validarEgresoManual", ControllerEgresos::aceptarEgreso);
        Spark.post("/rechazarValidacion", ControllerEgresos::rechazarEgreso);

        Spark.post("/validarEgreso", ControllerEgresos::redireccionarValidacion);

        //haciendo


        Spark.post("/cargarIngreso", ControllerIngresos::cargarIngreso);
        Spark.post("/asociarEgresosYPresupuestos", ControllerAsociacion::asociarEgresosYPresupuestos);

        Spark.post("/asociarIngresosYEgresos", ControllerAsociacion::asociarIngresosYEgresos);
        Spark.post("/crearCriterio", ControllerCriterio::crearCriterio);
        Spark.post("/crearCategoria", ControllerCategoria::crearCategoria);
        Spark.post("/cambiarContrasenia", ControllerUsuario::cambiarContrasenia);
        Spark.post("/cargarEntidad", ControllerEntidad::cargarEntidad);
        Spark.post("/cargarEgreso", ControllerEgresos::cargarEgreso);
        Spark.post("/cargarItemsEgreso", ControllerEgresos::cargarItem);
        Spark.post("/cargarProveedor", ControllerProveedor::cargarProveedor);
        Spark.post("/crearProyectoFinanciamiento", ControllerProyectoFinanciamiento::crearProyectoFinanciamiento);
        Spark.post("/administrarUsuarios", ControllerUsuario::administrarUsuarios);
        Spark.post("/cargarPresupuesto", ControllerPresupuesto::cargarPresupuesto);
        //checkeados
    }

    public static void levantarRutaDELETE(){

    }

    public static void levantarRutaPUT(){

    }

    public static void levantarRutaPATCH(){

    }
}

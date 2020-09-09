package Servidor;

import Servidor.Controllers.ControllerSesion;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
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
        Spark.get("/cerrarSesion", ControllerSesion::cerrarSesion);
    }

    public static void levantarRutaPOST(){
        Spark.post("/", ControllerSesion::validarLogin);
    }

    public static void levantarRutaDELETE(){

    }

    public static void levantarRutaPUT(){

    }

    public static void levantarRutaPATCH(){

    }
}

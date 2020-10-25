package Servidor.Controllers;

import com.google.common.base.Strings;
import org.eclipse.jetty.http.HttpStatus;
import spark.Filter;

import java.util.Optional;

import static spark.Spark.halt;

public class Autenticador {

    public static Filter authenticate = (request, response) -> {


        final Optional<String> maybeUser = Optional.ofNullable(request.session().attribute("idUsuarioActual"));
        final Optional<String> maybeXUser = Optional.ofNullable(request.headers("X-User"));

        if(!request.uri().equals("/") && !request.uri().equals("/autenticacion_usuario")&&!request.uri().equals("/validarLogin") ) { //Si es la barra no valido nada es la pantalla principal, o puede ser el login
            if (maybeUser.isPresent()) {

                System.out.println("encontraron el idusuario y es: "+maybeUser.orElse(""));
                // ya tenia un usuario en la session y si pasan X-User tiene que ser el mismo ya asignado

                maybeXUser.filter(xUser -> !xUser.equalsIgnoreCase(maybeUser.get()))
                        .ifPresent(xUser -> {halt(HttpStatus.NOT_FOUND_404);
                            System.out.println("encontraron el x-user y es: "+maybeXUser.orElse(""));
                        });//Sino cambiar por 401 que es sin autorizar

            } else {

                // no tenia usuario en la session y tienen que pasarlo en X-User para asignarlo

                if (!maybeXUser.isPresent() || maybeXUser.filter(Strings::isNullOrEmpty).isPresent()) {
                    halt(HttpStatus.NOT_FOUND_404);
                }
				/*
                final Optional<String> maybeUsuario = usuarioService.fetchUsuario(maybeXUser.get());
                if (maybeUsuario.isPresent()) {
                    request.session().attribute("user", maybeUsuario.get());
                } else {
                    halt(HttpStatus.UNAUTHORIZED_401);
                }
				 */
            }
        }
    };
}

package Servidor.Controllers;

import Dominio.Entidad.Direccion;
import Dominio.Entidad.DireccionPostal;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerDirecciones {
    public static DireccionPostal generarDireccion(String calle, String numeroCalle, String piso, String dpto, String pais, String provincia, String ciudad) {

        Direccion direccion = new Direccion(calle, numeroCalle, piso, dpto);

        DAO DAOPais = new DAOBBDD<Pais>(Pais.class);
        Repositorio repoPais = new Repositorio<Pais>(DAOPais);

        List<Pais> paises = repoPais.getTodosLosElementos();

        List<Pais> paisesList = paises.stream().filter(p -> p.getPais() == Integer.valueOf(pais)).collect(Collectors.toList());

        Pais p = paisesList.get(0);

        DAO DAOPcia = new DAOBBDD<Provincia>(Provincia.class);
        Repositorio repoPcia = new Repositorio<Provincia>(DAOPcia);

        List<Provincia> pcias = repoPcia.getTodosLosElementos();

        List<Provincia> pciasList = pcias.stream().filter(pv -> pv.getProvincia() == Integer.valueOf(provincia)).collect(Collectors.toList());

        Provincia pv = pciasList.get(0);

        DAO DAOCiudad = new DAOBBDD<Ciudad>(Ciudad.class);
        Repositorio repoCiudad = new Repositorio<Ciudad>(DAOCiudad);

        List<Ciudad> ciudades = repoCiudad.getTodosLosElementos();

        List<Ciudad> ciudadesList = ciudades.stream().filter(c -> c.getCiudad() == Integer.valueOf(ciudad)).collect(Collectors.toList());

        Ciudad c = ciudadesList.get(0);

        DireccionPostal direccionPostal = new DireccionPostal(direccion, 0, p, pv, c);

        return direccionPostal;
    }
}

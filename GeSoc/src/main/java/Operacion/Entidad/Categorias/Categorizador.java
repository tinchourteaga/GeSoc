package Operacion.Entidad.Categorias;

import Operacion.Entidad.Empresa;
import Operacion.Entidad.Sector;

public class Categorizador {
    private static DAOCategoria bolsaValores=new DAOMemoriaCategoria();

    public static DAOCategoria getBolsaValores() {
        return bolsaValores;
    }

    public static void setBolsaValores(DAOCategoria bolsaValores) {
        Categorizador.bolsaValores = bolsaValores;
    }

    public static void determinarCategoria(Empresa unaEmpresa) {

        if (unaEmpresa.getActividad().equals(Sector.INDUSTRIA_Y_MINERIA))
            unaEmpresa.categoria = determinarCategoriaIYM(unaEmpresa);

        if (unaEmpresa.getActividad().equals(Sector.AGROPECUARIO))
            unaEmpresa.categoria = determinarCategoriaAgropecuario(unaEmpresa);

        if (unaEmpresa.getActividad().equals(Sector.COMERCIO))
            unaEmpresa.categoria = determinarCategoriaComercio(unaEmpresa);

        if (unaEmpresa.getActividad().equals(Sector.CONSTRUCCION))
            unaEmpresa.categoria = determinarCategoriaConstrucion(unaEmpresa);


        if (unaEmpresa.getActividad().equals(Sector.SERVICIOS))
            unaEmpresa.categoria = determinarCategoriaServicios(unaEmpresa);

    }

    private static Categoria determinarCategoriaAgropecuario(Empresa unaEmpresa) {
        return null;
    }

    private static Categoria determinarCategoriaIYM(Empresa unaEmpresa) {
        return null;
    }

    private static Categoria determinarCategoriaServicios(Empresa unaEmpresa) {
        return null;
    }

    private static Categoria determinarCategoriaConstrucion(Empresa unaEmpresa) {
        return null;
    }

    private static Categoria determinarCategoriaComercio(Empresa unaEmpresa) {
        return null;
    }
}

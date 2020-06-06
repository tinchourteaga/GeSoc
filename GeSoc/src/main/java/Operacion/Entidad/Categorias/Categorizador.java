package Operacion.Entidad.Categorias;

import Operacion.Entidad.Empresa;
import Operacion.Entidad.Sector;
import com.sun.prism.paint.ImagePattern;

import java.util.HashMap;

public class Categorizador {
    private static DAOCategoria bolsaValores=new DAOMemoriaCategoria();
 static HashMap<KeyCategoria,Categoria> categorias = new HashMap<KeyCategoria,Categoria>();


    public static DAOCategoria getBolsaValores() {
        return bolsaValores;
    }

    public static void setBolsaValores(DAOCategoria bolsaValores) {
        Categorizador.bolsaValores = bolsaValores;
    }

    public static void determinarCategoria(Empresa unaEmpresa) {

        switch(unaEmpresa.getActividad()){
            case INDUSTRIA_Y_MINERIA:
            unaEmpresa.categoria = determinarCategoriaIYM(unaEmpresa);
            break;

            case AGROPECUARIO:
            unaEmpresa.categoria = determinarCategoriaAgropecuario(unaEmpresa);
            break;

            case COMERCIO:
            unaEmpresa.categoria = determinarCategoriaComercio(unaEmpresa);
            break;

            case CONSTRUCCION:
            unaEmpresa.categoria = determinarCategoriaConstrucion(unaEmpresa);
            break;

            case SERVICIOS:
            unaEmpresa.categoria = determinarCategoriaServicios(unaEmpresa);
            break;

        }

    }

    private static Categoria determinarCategoriaAgropecuario(Empresa unaEmpresa) {

        float ventasAnualesTramo1=bolsaValores.getVentasAnualesTramo1Agropecuario();
        float ventasAnualesPequenia=bolsaValores.getVentasAnualesPequeniaAgropecuario();
        float ventasAnualesMicro=bolsaValores.getventasAnualesMicroAgropecuario();
        int personalOcupadoMicro=bolsaValores.getPersonalOcupadoMicroAgropecuario();
        int personalOcupadoPequenia=bolsaValores.getPersonalOcupadoPequeniaAgropecuario();
        int personalOcupadoTramo1=bolsaValores.getPersonalOcupadoTramo1Agropecuario();
        float ventasAnualesTramo2=bolsaValores.getVentasAnualesTramo2Agropecuario();
        int personalOcupadoTramo2=bolsaValores.getpersonalOcupadoTramo2Agropecuario();
        //deberia delegarlo en un objeto?
        return determinarTamanio(unaEmpresa, ventasAnualesTramo1, ventasAnualesPequenia,
         ventasAnualesMicro, personalOcupadoMicro, personalOcupadoPequenia, personalOcupadoTramo1,
         ventasAnualesTramo2, personalOcupadoTramo2);
    }

    private static Categoria determinarCategoriaIYM(Empresa unaEmpresa) {
        float ventasAnualesTramo1=bolsaValores.getVentasAnualesTramo1IYM();
        float ventasAnualesPequenia=bolsaValores.getVentasAnualesPequeniaIYM();
        float ventasAnualesMicro=bolsaValores.getventasAnualesMicroIYM();
        int personalOcupadoMicro=bolsaValores.getPersonalOcupadoMicroIYM();
        int personalOcupadoPequenia=bolsaValores.getPersonalOcupadoPequeniaIYM();
        int personalOcupadoTramo1=bolsaValores.getPersonalOcupadoTramo1IYM();
        float ventasAnualesTramo2=bolsaValores.getVentasAnualesTramo2IYM();
        int personalOcupadoTramo2=bolsaValores.getpersonalOcupadoTramo2IYM();
        //deberia delegarlo en un objeto?
        return determinarTamanio(unaEmpresa, ventasAnualesTramo1, ventasAnualesPequenia,
                ventasAnualesMicro, personalOcupadoMicro, personalOcupadoPequenia, personalOcupadoTramo1,
                ventasAnualesTramo2, personalOcupadoTramo2);
    }

    private static Categoria determinarCategoriaServicios(Empresa unaEmpresa) {
        float ventasAnualesTramo1=bolsaValores.getVentasAnualesTramo1Servicios();
        float ventasAnualesPequenia=bolsaValores.getVentasAnualesPequeniaServicios();
        float ventasAnualesMicro=bolsaValores.getventasAnualesMicroServicios();
        int personalOcupadoMicro=bolsaValores.getPersonalOcupadoMicroServicios();
        int personalOcupadoPequenia=bolsaValores.getPersonalOcupadoPequeniaServicios();
        int personalOcupadoTramo1=bolsaValores.getPersonalOcupadoTramo1Servicios();
        float ventasAnualesTramo2=bolsaValores.getVentasAnualesTramo2Servicios();
        int personalOcupadoTramo2=bolsaValores.getpersonalOcupadoTramo2Servicios();
        //deberia delegarlo en un objeto?
        return determinarTamanio(unaEmpresa, ventasAnualesTramo1, ventasAnualesPequenia,
                ventasAnualesMicro, personalOcupadoMicro, personalOcupadoPequenia, personalOcupadoTramo1,
                ventasAnualesTramo2, personalOcupadoTramo2);
    }

    private static Categoria determinarCategoriaConstrucion(Empresa unaEmpresa) {
        float ventasAnualesTramo1=bolsaValores.getVentasAnualesTramo1Construcion();
        float ventasAnualesPequenia=bolsaValores.getVentasAnualesPequeniaConstrucion();
        float ventasAnualesMicro=bolsaValores.getventasAnualesMicroConstrucion();
        int personalOcupadoMicro=bolsaValores.getPersonalOcupadoMicroConstrucion();
        int personalOcupadoPequenia=bolsaValores.getPersonalOcupadoPequeniaConstrucion();
        int personalOcupadoTramo1=bolsaValores.getPersonalOcupadoTramo1Construcion();
        float ventasAnualesTramo2=bolsaValores.getVentasAnualesTramo2Construcion();
        int personalOcupadoTramo2=bolsaValores.getpersonalOcupadoTramo2Construcion();

        return determinarTamanio(unaEmpresa, ventasAnualesTramo1, ventasAnualesPequenia,
                ventasAnualesMicro, personalOcupadoMicro, personalOcupadoPequenia, personalOcupadoTramo1,
                ventasAnualesTramo2, personalOcupadoTramo2);
    }

    private static Categoria determinarCategoriaComercio(Empresa unaEmpresa) {
        float ventasAnualesTramo1=bolsaValores.getVentasAnualesTramo1Comercio();
        float ventasAnualesPequenia=bolsaValores.getVentasAnualesPequeniaComercio();
        float ventasAnualesMicro=bolsaValores.getventasAnualesMicroComercio();
        int personalOcupadoMicro=bolsaValores.getPersonalOcupadoMicroComercio();
        int personalOcupadoPequenia=bolsaValores.getPersonalOcupadoPequeniaComercio();
        int personalOcupadoTramo1=bolsaValores.getPersonalOcupadoTramo1Comercio();
        float ventasAnualesTramo2=bolsaValores.getVentasAnualesTramo2Comercio();
        int personalOcupadoTramo2=bolsaValores.getpersonalOcupadoTramo2Comercio();

        return determinarTamanio(unaEmpresa, ventasAnualesTramo1, ventasAnualesPequenia,
                ventasAnualesMicro, personalOcupadoMicro, personalOcupadoPequenia, personalOcupadoTramo1,
                ventasAnualesTramo2, personalOcupadoTramo2);
    }
    private static Categoria determinarTamanio(Empresa empresa,float ventasAnualesTramo1,float ventasAnualesPequenia,
                                               float ventasAnualesMicro,int personalOcupadoMicro,int personalOcupadoPequenia,int personalOcupadoTramo1,
                                               float ventasAnualesTramo2,int personalOcupadoTramo2) {
        if (empresa.getCantidadPersonal() < personalOcupadoMicro || empresa.getPromedioVentasAnuales() < ventasAnualesMicro) {
            return getcategoriaMicro(empresa.getActividad());
        }
        if (empresa.getCantidadPersonal() < personalOcupadoPequenia || empresa.getPromedioVentasAnuales() < ventasAnualesPequenia) {
            return getcategoriaPequenia(empresa.getActividad());
        }
        if (empresa.getCantidadPersonal() < personalOcupadoTramo1 || empresa.getPromedioVentasAnuales() < ventasAnualesTramo1) {
            return getcategoriaMedianaTramo1(empresa.getActividad());
        }
        return getcategoriaMedianaTramo2(empresa.getActividad());
    }

    private static Categoria getcategoriaMedianaTramo1(Sector actividad) {
        return categorias.get(new KeyCategoria(TipoCategoria.MEDIANA_TRAMO_1,actividad));
    }

    private static Categoria getcategoriaMedianaTramo2(Sector actividad) {
        return categorias.get(new KeyCategoria(TipoCategoria.MEDIANA_TRAMO_2,actividad));
    }

    private static Categoria getcategoriaMicro(Sector actividad) {
        return categorias.get(new KeyCategoria(TipoCategoria.MICRO,actividad));
    }

    private static Categoria getcategoriaPequenia(Sector actividad) {
        return categorias.get(new KeyCategoria(TipoCategoria.PEQUENIA,actividad));
    }

    private static class KeyCategoria {
        TipoCategoria tipoCategoria;
        Sector sector;

          public KeyCategoria(TipoCategoria tipoCategoria,
                  Sector sector){
              this.tipoCategoria=tipoCategoria;
              this.sector=sector;
          }

    }
}

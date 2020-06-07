package Operacion.Entidad.Categorias;

import java.util.HashMap;

public interface DAOCategoria {

    HashMap<KeyCategoria,Categoria>  obtenerMapaDeCategorias();

    //DATOS AGROPECUARIO
    float getVentasAnualesTramo1Agropecuario();
    float getVentasAnualesPequeniaAgropecuario();
    float getventasAnualesMicroAgropecuario();
    int getPersonalOcupadoMicroAgropecuario();
    int getPersonalOcupadoPequeniaAgropecuario();
    int getPersonalOcupadoTramo1Agropecuario();
    float getVentasAnualesTramo2Agropecuario();
    int getpersonalOcupadoTramo2Agropecuario();

    //DATOS IYM
    float getVentasAnualesTramo1IYM();
    float getVentasAnualesPequeniaIYM();
    float getventasAnualesMicroIYM();
    int getPersonalOcupadoMicroIYM();
    int getPersonalOcupadoPequeniaIYM();
    int getPersonalOcupadoTramo1IYM();
    float getVentasAnualesTramo2IYM();
    int getpersonalOcupadoTramo2IYM();
    //DATOS Comercio
    float getVentasAnualesTramo1Comercio();
    float getVentasAnualesPequeniaComercio();
    float getventasAnualesMicroComercio();
    int getPersonalOcupadoMicroComercio();
    int getPersonalOcupadoPequeniaComercio();
    int getPersonalOcupadoTramo1Comercio();
    float getVentasAnualesTramo2Comercio();
    int getpersonalOcupadoTramo2Comercio();
    //DATOS Servicios
    float getVentasAnualesTramo1Servicios();
    float getVentasAnualesPequeniaServicios();
    float getventasAnualesMicroServicios();
    int getPersonalOcupadoMicroServicios();
    int getPersonalOcupadoPequeniaServicios();
    int getPersonalOcupadoTramo1Servicios();
    float getVentasAnualesTramo2Servicios();
    int getpersonalOcupadoTramo2Servicios();
    //DATOS COSNTRUCCIONES
    float getVentasAnualesTramo1Construccion();
    float getVentasAnualesPequeniaConstruccion();
    float getventasAnualesMicroConstruccion();
    int getPersonalOcupadoMicroConstruccion();
    int getPersonalOcupadoPequeniaConstruccion();
    int getPersonalOcupadoTramo1Construccion();
    float getVentasAnualesTramo2Construccion();
    int getpersonalOcupadoTramo2Construccion();


}

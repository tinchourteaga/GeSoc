package Dominio.Egreso.Entidad.Categorias;

import Dominio.Egreso.Entidad.Sector;
import java.util.HashMap;

public class DAOMemoriaCategoria implements DAOCategoria {


    @Override
    public HashMap<String, Categoria> obtenerMapaDeCategorias() {
        HashMap<String, Categoria> nuevoMap= new HashMap<String, Categoria>();

        //las 4 agropecuarias
        nuevoMap.put(TipoCategoria.MICRO.toString()+"-"+Sector.AGROPECUARIO.toString(),
                new Categoria(Sector.AGROPECUARIO,TipoCategoria.MICRO,
                        this.getPersonalOcupadoMicroAgropecuario(),
                        this.getventasAnualesMicroAgropecuario()));

        nuevoMap.put(TipoCategoria.PEQUENIA.toString()+"-"+Sector.AGROPECUARIO.toString(),
                new Categoria(Sector.AGROPECUARIO,TipoCategoria.PEQUENIA,
                        this.getPersonalOcupadoPequeniaAgropecuario(),
                        this.getVentasAnualesPequeniaAgropecuario()));

        nuevoMap.put(TipoCategoria.MEDIANA_TRAMO_1.toString()+"-"+Sector.AGROPECUARIO.toString(),
                new Categoria(Sector.AGROPECUARIO,TipoCategoria.MEDIANA_TRAMO_1,
                        this.getPersonalOcupadoTramo1Agropecuario(),
                        this.getVentasAnualesTramo1Agropecuario()));

        nuevoMap.put(TipoCategoria.MEDIANA_TRAMO_2.toString()+"-"+ Sector.AGROPECUARIO.toString(),
                new Categoria(Sector.AGROPECUARIO,TipoCategoria.MEDIANA_TRAMO_2,
                        this.getpersonalOcupadoTramo2Agropecuario(),
                        this.getVentasAnualesTramo2Agropecuario()));

        //las 4 iym
        nuevoMap.put((TipoCategoria.MICRO.toString()+"-"+ Sector.INDUSTRIA_Y_MINERIA.toString()),
                new Categoria(Sector.INDUSTRIA_Y_MINERIA,TipoCategoria.MICRO,
                        this.getPersonalOcupadoMicroIYM(),
                        this.getventasAnualesMicroIYM()));

        nuevoMap.put((TipoCategoria.PEQUENIA.toString()+"-"+ Sector.INDUSTRIA_Y_MINERIA.toString()),
                new Categoria(Sector.INDUSTRIA_Y_MINERIA,TipoCategoria.PEQUENIA,
                        this.getPersonalOcupadoPequeniaIYM(),
                        this.getVentasAnualesPequeniaIYM()));

        nuevoMap.put( (TipoCategoria.MEDIANA_TRAMO_1.toString()+"-"+ Sector.INDUSTRIA_Y_MINERIA.toString()),
                new Categoria(Sector.INDUSTRIA_Y_MINERIA,TipoCategoria.MEDIANA_TRAMO_1,
                        this.getPersonalOcupadoTramo1IYM(),
                        this.getVentasAnualesTramo1IYM()));

        nuevoMap.put( (TipoCategoria.MEDIANA_TRAMO_2.toString()+"-"+  Sector.INDUSTRIA_Y_MINERIA.toString()),
                new Categoria(Sector.INDUSTRIA_Y_MINERIA,TipoCategoria.MEDIANA_TRAMO_2,
                        this.getpersonalOcupadoTramo2IYM(),
                        this.getVentasAnualesTramo2IYM()));

        //las 4 de servicios
        nuevoMap.put( (TipoCategoria.MICRO.toString()+"-"+  Sector.SERVICIOS.toString()),
                new Categoria(Sector.SERVICIOS,TipoCategoria.MICRO,
                        this.getPersonalOcupadoMicroServicios(),
                        this.getventasAnualesMicroServicios()));

        nuevoMap.put( (TipoCategoria.PEQUENIA.toString()+"-"+  Sector.SERVICIOS.toString()),
                new Categoria(Sector.SERVICIOS,TipoCategoria.PEQUENIA,
                        this.getPersonalOcupadoPequeniaServicios(),
                        this.getVentasAnualesPequeniaServicios()));

        nuevoMap.put( (TipoCategoria.MEDIANA_TRAMO_1.toString()+"-"+  Sector.SERVICIOS.toString()),
                new Categoria(Sector.SERVICIOS,TipoCategoria.MEDIANA_TRAMO_1,
                        this.getPersonalOcupadoTramo1Servicios(),
                        this.getVentasAnualesTramo1Servicios()));

        nuevoMap.put( (TipoCategoria.MEDIANA_TRAMO_2.toString()+"-"+  Sector.SERVICIOS.toString()),
                new Categoria(Sector.SERVICIOS,TipoCategoria.MEDIANA_TRAMO_2,
                        this.getpersonalOcupadoTramo2Servicios(),
                        this.getVentasAnualesTramo2Servicios()));

        //las 4 construcciones
        nuevoMap.put( (TipoCategoria.MICRO.toString()+"-"+  Sector.CONSTRUCCION.toString()),
                new Categoria(Sector.CONSTRUCCION,TipoCategoria.MICRO,
                        this.getPersonalOcupadoMicroConstruccion(),
                        this.getventasAnualesMicroConstruccion()));

        nuevoMap.put( (TipoCategoria.PEQUENIA.toString()+"-"+  Sector.CONSTRUCCION.toString()),
                new Categoria(Sector.CONSTRUCCION,TipoCategoria.PEQUENIA,
                        this.getPersonalOcupadoPequeniaConstruccion(),
                        this.getVentasAnualesPequeniaConstruccion()));

        nuevoMap.put( (TipoCategoria.MEDIANA_TRAMO_1.toString()+"-"+  Sector.CONSTRUCCION.toString()),
                new Categoria(Sector.CONSTRUCCION,TipoCategoria.MEDIANA_TRAMO_1,
                        this.getPersonalOcupadoTramo1Construccion(),
                        this.getVentasAnualesTramo1Construccion()));

        nuevoMap.put(TipoCategoria.MEDIANA_TRAMO_2.toString()+"-"+Sector.CONSTRUCCION.toString(),
                new Categoria(Sector.CONSTRUCCION,TipoCategoria.MEDIANA_TRAMO_2,
                        this.getpersonalOcupadoTramo2Construccion(),
                        this.getVentasAnualesTramo2Construccion()));

        //las 4 de comercio
        nuevoMap.put( (TipoCategoria.MICRO.toString()+"-"+  Sector.COMERCIO.toString()),
                new Categoria(Sector.COMERCIO,TipoCategoria.MICRO,
                        this.getPersonalOcupadoMicroComercio(),
                        this.getventasAnualesMicroComercio()));

        nuevoMap.put( (TipoCategoria.PEQUENIA.toString()+"-"+  Sector.COMERCIO.toString()),
                new Categoria(Sector.COMERCIO,TipoCategoria.PEQUENIA,
                        this.getPersonalOcupadoPequeniaComercio(),
                        this.getVentasAnualesPequeniaComercio()));

        nuevoMap.put((TipoCategoria.MEDIANA_TRAMO_1.toString()+"-"+  Sector.COMERCIO.toString()),
                new Categoria(Sector.COMERCIO,TipoCategoria.MEDIANA_TRAMO_1,
                        this.getPersonalOcupadoTramo1Comercio(),
                        this.getVentasAnualesTramo1Comercio()));

        nuevoMap.put((TipoCategoria.MEDIANA_TRAMO_2.toString()+"-"+  Sector.COMERCIO.toString()),
                new Categoria(Sector.COMERCIO,TipoCategoria.MEDIANA_TRAMO_2,
                        this.getpersonalOcupadoTramo2Comercio(),
                        this.getVentasAnualesTramo2Comercio()));

        return nuevoMap;
    }

    //datos Agropecuarios
    @Override
    public float getVentasAnualesTramo1Agropecuario() {
        return 345430000;
    }
    @Override
    public float getVentasAnualesPequeniaAgropecuario() {
        return 48480000;
    }
    @Override
    public float getventasAnualesMicroAgropecuario() {
        return 12890000;
    }
    @Override
    public int getPersonalOcupadoMicroAgropecuario() {
        return 5;
    }
    @Override
    public int getPersonalOcupadoPequeniaAgropecuario() {
        return 10;
    }
    @Override
    public int getPersonalOcupadoTramo1Agropecuario() {
        return 50;
    }
    @Override
    public float getVentasAnualesTramo2Agropecuario() {
        return 547890000;
    }
    @Override
    public int getpersonalOcupadoTramo2Agropecuario() {
        return 215;
    }



    //DATOS IYM

    @Override
    public float getVentasAnualesTramo1IYM() {
        return 1190330000;
    }
    @Override
    public float getVentasAnualesPequeniaIYM() {
        return 190410000;
    }
    @Override
    public float getventasAnualesMicroIYM() {
        return 26540000;
    }
    @Override
    public int getPersonalOcupadoMicroIYM() {
        return 15;
    }
    @Override
    public int getPersonalOcupadoPequeniaIYM() {
        return 60;
    }
    @Override
    public int getPersonalOcupadoTramo1IYM() {
        return 235;
    }
    @Override
    public float getVentasAnualesTramo2IYM() {
        return 1739590000;
    }
    @Override
    public int getpersonalOcupadoTramo2IYM() {
        return 655;
    }


    //datos comercio
    @Override
    public float getVentasAnualesTramo1Comercio() {
        return 1502750000;
    }
    @Override
    public float getVentasAnualesPequeniaComercio() {
        return 178860000;
    }
    @Override
    public float getventasAnualesMicroComercio() {
        return 29740000;
    }
    @Override
    public int getPersonalOcupadoMicroComercio() {
        return 7;
    }
    @Override
    public int getPersonalOcupadoPequeniaComercio() {
        return 35;
    }
    @Override
    public int getPersonalOcupadoTramo1Comercio() {
        return 125;
    }
    @Override
    public float getVentasAnualesTramo2Comercio() {
        return 2146810000;
    }
    @Override
    public int getpersonalOcupadoTramo2Comercio() {
        return 345;
    }


    //datos servicios

    @Override
    public float getVentasAnualesTramo1Servicios() {
        return 425170000;
    }
    @Override
    public float getVentasAnualesPequeniaServicios() {
        return 50950000;
    }
    @Override
    public float getventasAnualesMicroServicios() {
        return 8500000;
    }
    @Override
    public int getPersonalOcupadoMicroServicios() {
        return 7;
    }
    @Override
    public int getPersonalOcupadoPequeniaServicios() {
        return 35;
    }
    @Override
    public int getPersonalOcupadoTramo1Servicios() {
        return 165;
    }
    @Override
    public float getVentasAnualesTramo2Servicios() {
        return 607210000;
    }
    @Override
    public int getpersonalOcupadoTramo2Servicios() {
        return 535;
    }



    //datos Construccion
    @Override
    public float getVentasAnualesTramo1Construccion() {
        return 503880000;
    }
    @Override
    public float getVentasAnualesPequeniaConstruccion() {
        return 90310000;
    }
    @Override
    public float getventasAnualesMicroConstruccion() {
        return 15230000;
    }
    @Override
    public int getPersonalOcupadoMicroConstruccion() {
        return 12;
    }
    @Override
    public int getPersonalOcupadoPequeniaConstruccion() {
        return 45;
    }
    @Override
    public int getPersonalOcupadoTramo1Construccion() {
        return 200;
    }
    @Override
    public float getVentasAnualesTramo2Construccion() {
        return 755740000;
    }
    @Override
    public int getpersonalOcupadoTramo2Construccion() {
        return 590;
    }
}

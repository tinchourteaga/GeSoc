package Operacion.Entidad;

import java.util.ArrayList;
import java.util.List;

public class Actividad {
    TipoActividad tipo;
    float ventasAnualesMicro;
    float ventasAnualesPequenia;
    float ventasAnualesTramo1;
    float ventasAnualesTramo2;

    int personalOcupadoMicro;
    int personalOcupadoPequenia;
    int personalOcupadoTramo1;
    int personalOcupadoTramo2;

    public Actividad(TipoActividad tipo){
        this.tipo=tipo;
        switch (tipo){
            case COMERCIO:
                 ventasAnualesMicro=29740000;
                 ventasAnualesPequenia=178860000;
                 ventasAnualesTramo1=1502750000;
                 ventasAnualesTramo2=2146810000;

                 personalOcupadoMicro=7;
                 personalOcupadoPequenia=35;
                 personalOcupadoTramo1=125;
                 personalOcupadoTramo2=345;
                break;

            case SERVICIOS:
                 ventasAnualesMicro=8500000;
                 ventasAnualesPequenia=50950000;
                 ventasAnualesTramo1=425170000;
                 ventasAnualesTramo2=607210000;

                 personalOcupadoMicro=7;
                 personalOcupadoPequenia=30;
                 personalOcupadoTramo1=165;
                 personalOcupadoTramo2=535;
                break;

            case AGROPECUARIO:
                ventasAnualesMicro=12890000;
                ventasAnualesPequenia=48480000;
                ventasAnualesTramo1=345430000;
                ventasAnualesTramo2=547890000;

                personalOcupadoMicro=5;
                personalOcupadoPequenia=10;
                personalOcupadoTramo1=50;
                personalOcupadoTramo2=215;
                break;

            case CONSTRUCCION:
                ventasAnualesMicro=15230000;
                ventasAnualesPequenia=90310000;
                ventasAnualesTramo1=503880000;
                ventasAnualesTramo2=755740000;

                personalOcupadoMicro=12;
                personalOcupadoPequenia=45;
                personalOcupadoTramo1=200;
                personalOcupadoTramo2=590;
                break;

            case INDUSTRIA_Y_MINERIA:
                ventasAnualesMicro=26540000;
                ventasAnualesPequenia=190410000;
                ventasAnualesTramo1=1190330000;
                ventasAnualesTramo2=1739590000;

                personalOcupadoMicro=15;
                personalOcupadoPequenia=60;
                personalOcupadoTramo1=235;
                personalOcupadoTramo2=655;
                break;
        }

    }

    public Categoria calcularCategoria(Empresa empresa) {

        if(empresa.cantidadPersonal<personalOcupadoMicro|| empresa.promedioVentasAnuales<ventasAnualesMicro){
            return new Micro();
        }
        if(empresa.cantidadPersonal<personalOcupadoPequenia || empresa.promedioVentasAnuales<ventasAnualesPequenia){
            return new Pequenia();
        }
        if(empresa.cantidadPersonal<personalOcupadoTramo1|| empresa.promedioVentasAnuales<ventasAnualesTramo1){
            return new MedianaTramo1();
        }
        if(empresa.cantidadPersonal<personalOcupadoTramo2 || empresa.promedioVentasAnuales<ventasAnualesTramo2){
            return new MedianaTramo2();
        }

       return null;
    }
}

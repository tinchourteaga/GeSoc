package Egreso.Core;

import Egreso.Core.Egreso;

public class Item {
    Float valor;
    String descripcion;

    public Float getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Item(Float importe,String desc){
        this.valor=importe;
        this.descripcion=desc;
    }
}


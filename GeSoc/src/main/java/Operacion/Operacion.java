package Operacion;

import Usuario.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Operacion {
    Date fecha;
    Float valor;
    List<Item> listaItems = new ArrayList<Item>();
    MetodoPago metodoPago;
    Proveedor proveedor;
    DocumentoComercial documentoComercial;
    
    public Operacion(Date unaFecha,float importe,List<Item> items,MetodoPago metodo,Proveedor unProveedor, DocumentoComercial unDocumento){
       this.fecha=unaFecha;
       this.valor=importe;
       this.listaItems=items;
       this.metodoPago=metodo;
       this.proveedor=unProveedor;
       this.documentoComercial=unDocumento;
        
        
    }

    public void realizar() {
        //esto no lo implemento porque toda
    }

    //esto es de 2da entrega no lo implemento todavia
    public void revisar(Usuario usuario) {
    }
    public void validar(){
    }
}

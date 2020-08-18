package Dominio.Entidad;

import API.ControllerMercadoLibre;
import API.DTOs.ZipCodeDTO;
import API.Excepciones.ExcepcionCodigoNoEncontrado;

import java.io.IOException;

public class DireccionPostal {
    private Direccion direccion;
    private int cp;
    private String pais;
    private String provincia;
    private String ciudad;

    public DireccionPostal(Direccion direccion, int cp, String pais) {

        ControllerMercadoLibre controller;
        ZipCodeDTO zipCodeDTO;

        this.direccion = direccion;
        this.cp = cp;
        this.pais = pais;
        controller = ControllerMercadoLibre.getControllerMercadoLibre();
        try {
            zipCodeDTO = controller.pedirInformacionCodigoPostal(pais,String.valueOf(cp));
            this.provincia = zipCodeDTO.getState().getName();
            this.ciudad = zipCodeDTO.getCity().getName();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionCodigoNoEncontrado excepcionCodigoNoEncontrado) {
            excepcionCodigoNoEncontrado.printStackTrace();
        }
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public int getCp() {
        return cp;
    }

    public String getPais() {
        return pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCiudad() {
        return ciudad;
    }
}

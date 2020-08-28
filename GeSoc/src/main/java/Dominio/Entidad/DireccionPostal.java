package Dominio.Entidad;

import API.ControllerMercadoLibre;
import API.DTOs.Ciudad;
import API.DTOs.Pais;
import API.DTOs.Provincia;
import API.DTOs.ZipCodeDTO;
import API.Excepciones.ExcepcionCodigoNoEncontrado;

import java.io.IOException;
import java.nio.file.ProviderNotFoundException;

public class DireccionPostal {
    private Direccion direccion;
    private int cp;
    private Pais pais;
    private Provincia provincia;
    private Ciudad ciudad;

    public DireccionPostal(Direccion direccion, int cp, Pais pais) {

        ControllerMercadoLibre controller;
        ZipCodeDTO zipCodeDTO;

        this.direccion = direccion;
        this.cp = cp;
        this.pais = pais;
        controller = ControllerMercadoLibre.getControllerMercadoLibre();
        try {
            zipCodeDTO = controller.pedirInformacionCodigoPostal(pais,String.valueOf(cp));
            this.provincia = zipCodeDTO.getState();
            this.ciudad = zipCodeDTO.getCity();
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
}

package Dominio.Contrasenia.DAO;

import Dominio.Contrasenia.Core.IValidacion;

import java.util.List;

public interface DAOValidacion {

    public List<IValidacion> obtenerValidaciones();

    public IValidacion obtenerChequearContraseniaComun();

    public IValidacion obtenerChequearLongitudContrasenia();

    public IValidacion obtenerContieneCaracterEspecial();

    public IValidacion obtenerContieneNumero();

}

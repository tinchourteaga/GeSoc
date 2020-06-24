package Contrasenia.DAO;

import Contrasenia.Core.*;

import java.util.ArrayList;
import java.util.List;

public class MemoriaValidacion implements DAOValidacion{
    @Override
    public List<IValidacion> obtenerValidaciones() {
        return new ArrayList() {{
            add(obtenerChequearContraseniaComun());
            add(obtenerChequearLongitudContrasenia());
            add(obtenerContieneCaracterEspecial());
            add(obtenerContieneNumero());
        }};
    }
    @Override
    public IValidacion obtenerChequearContraseniaComun() {
        return new ChequearContraseniaComun();
    }

    @Override
    public IValidacion obtenerChequearLongitudContrasenia() {
        return new ChequearLongitudContrasenia();
    }

    @Override
    public IValidacion obtenerContieneCaracterEspecial() {
        return new ContieneCaracterEspecial();
    }

    @Override
    public IValidacion obtenerContieneNumero() {
        return new ContieneNumero();
    }
}

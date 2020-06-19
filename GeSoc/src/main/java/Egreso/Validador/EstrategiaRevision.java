package Egreso.Validador;

import Egreso.Core.Egreso;
import Usuario.Usuario;

public interface EstrategiaRevision {
   public void revisar(Egreso operacion, Usuario usuario);
}

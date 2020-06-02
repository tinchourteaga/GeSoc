package Operacion.Validador;

import Operacion.Core.Operacion;
import Usuario.Usuario;

public interface EstrategiaRevision {
   public void revisar(Operacion operacion, Usuario usuario);
}

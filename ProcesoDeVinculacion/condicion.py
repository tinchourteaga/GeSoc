
import abc
from importe import Importe

class Condicion(metaclass=abc.ABCMeta):
    valor = 0
    @abc.abstractmethod
    def cumple(self,ingreso:Importe,egreso:Importe):
        pass

class PeriodoAceptacion(Condicion):
    def __init__(self, valor):
        self.valor = valor

    def cumple(self,ingreso: Importe,egreso:Importe) -> bool:
         if (ingreso.getFecha() - egreso.getFecha()).days < self.valor :
            return True
         else:
            return False


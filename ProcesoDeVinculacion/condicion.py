
import abc
from importe import Importe

class Condicion(metaclass=abc.ABCMeta):
    valor = 0
    @abc.abstractmethod
    def cumple(self,ingreso:Importe,egreso:Importe):
        pass

class PeriodoAceptacion(Condicion):

    def __init__(self, parametros: list):
        self.valor = parametros[0]

    def cumple(self,ingreso: Importe,egreso:Importe) -> bool:
         if (ingreso.getFecha() - egreso.getFecha()).days < self.valor :
            return True
         else:
            return False

    def getValor(self):
        return self.valor
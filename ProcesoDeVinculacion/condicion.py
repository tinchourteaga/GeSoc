
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
         print("Fecha Ingreso ",ingreso.getFecha()," Fecha Egreso ",egreso.getFecha())
         print("cuenta ", (ingreso.getFecha() - egreso.getFecha()).days, " periodo_dias ", self.valor)
         if (ingreso.getFecha() - egreso.getFecha()).days < self.valor:
            print("cumple")
            return True
         else:
            print("Nocumple")
            return False

    def getValor(self):
        return self.valor

    def getJsonFormat(self):
        return "{" + str(self.__class__.__name__) + ":{" + str(self.valor) + "}}"
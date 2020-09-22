
import abc
class Criterio(metaclass=abc.ABCMeta):
    @abc.abstractmethod
    def aplicar(self,ingresos, egresos, condicion):
        pass

class OrdenValorPrimeroEgreso(Criterio):
    def aplicar(self,ingresos, egresos, condicion):
        print("OrdenValorPrimeroEgreso")


class OrdenValorPrimeroIgreso(Criterio):
    def aplicar(self,ingresos, egresos,condicion):
        print("OrdenValorPrimeroIgreso")
        ingresos.sort(key=lambda x: x.getValor())
        egresos.sort(key=lambda x: x.getValor())
        resultado=[dict()]

        for ingreso in ingresos:
            tempDict = {
                "codigoIngreso": ingreso.getCodigo(),
                "codiosEgresos": [],
            }
            for egreso in egresos:
                if ingreso.esImporteAplicable(egreso.getValor()) and condicion.cumple(ingreso,egreso):
                    ingreso.RestarImporte(egreso.getValor())
                    tempDict["codiosEgresos"].append(egreso.getCodigo())
                    egresos.remove(egreso)
            resultado.append(tempDict)



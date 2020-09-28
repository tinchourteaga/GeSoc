
import abc

import condicion
from condiciones import Condiciones


class Criterio(metaclass=abc.ABCMeta):
    @abc.abstractmethod
    def aplicar(self,ingresos, egresos, condicion):
        pass

class OrdenValorPrimeroEgreso(Criterio):

    def aplicar(self,ingresos, egresos, condiciones: Condiciones):
        print("OrdenValorPrimeroEgreso")


class OrdenValorPrimeroIngreso(Criterio):
    def aplicar(self, ingresos, egresos, condiciones: Condiciones):
        print("OrdenValorPrimeroIngreso")
        ingresos.sort(key=lambda x: x.getValor())
        egresos.sort(key=lambda x: x.getValor())
        resultado=[dict()]
        i=0
        largoListaIngresos = len(ingresos)-1
        while i < largoListaIngresos:
            print("largoListaIngresos ", largoListaIngresos)
            ingreso = ingresos[i]
            tempDict = {
                "codigoIngreso": ingreso.getCodigo(),
                "codigosEgresos": [],
            }
            j = 0
            largoListaEgresos = len(egresos)-1
            while j < largoListaEgresos:
                print("largoListaEgresos ", largoListaEgresos)
                egreso = egresos[j]
                if ingreso.esImporteAplicable(egreso.getValor()) and condiciones.cumple(ingreso,egreso):
                    ingresos[i].RestarImporte(egreso.getValor())
                    print(ingresos[i].get)
                    tempDict["codigosEgresos"].append(egreso.getCodigo())
                    egresos.remove(egreso[j])
                    largoListaEgresos -= 1
                else:
                    j +=1
            if len(tempDict.get("codigosEgresos")) != 0:
                resultado.append(tempDict)
                ingresos.remove(ingreso[i])
                largoListaIngresos -=1
            else:
                i +=1
        print("resultado parcial",resultado[0])
        return resultado


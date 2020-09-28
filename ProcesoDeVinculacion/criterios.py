
from condiciones import Condiciones
from criterio import OrdenValorPrimeroIngreso, OrdenValorPrimeroEgreso


class Criterios:
    criterios=[]

    def __init__(self, criterios: list):
        for criterio in criterios:
            if criterio == "OrdenValorPrimeroIngreso":
                temp = OrdenValorPrimeroIngreso()
            elif criterio == "OrdenValorPrimeroEgreso":
                temp = OrdenValorPrimeroEgreso()
            self.addCriterio(temp)


    def aplicar(self, ingresos: list, egresos: list, condiciones: Condiciones):
        result=[]
        for criterio in self.criterios:
            result.append(criterio.aplicar(ingresos, egresos, condiciones))
        return result

    def addCriterio(self,criterio):
        self.criterios.append(criterio)
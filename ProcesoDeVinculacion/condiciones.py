from condicion import PeriodoAceptacion
from importe import Importe


class Condiciones:
    condiciones=[]

    def __init__(self, condiciones: list):
        for condicion in condiciones:
            temp=""
            if condicion.get("nombreCondicion") == "PeriodoAceptacion":
                temp = PeriodoAceptacion(condicion.get('parametros'))
            self.addCondicion(temp)

    def cumple(self, ingreso: Importe, egreso: Importe):
        i=0
        while i <= len(self.condiciones):
            result= self.condiciones[i].cumple(ingreso,egreso)
            if not result:
                break;
        return result

    def addCondicion(self,condicion):
        self.condiciones.append(condicion)
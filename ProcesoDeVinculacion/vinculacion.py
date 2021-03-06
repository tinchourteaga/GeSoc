from flask import Flask, request, jsonify, json

from condicion import Condicion
from importe import Importe


class Vinculacion:
    # La juega de ingreso o de egreso (segun convenga)
    movimientoAsociado : Importe = ""

    # La juega de egresos o de ingresos(segun convenga)
    vinculados : Importe = []

    criterio = ""

    condiciones : Condicion = []

    def __init__(self, unMovimientoAsociado, unCriterio):
        self.criterio = unCriterio
        self.movimientoAsociado = unMovimientoAsociado

    def agregarVinculado(self, unVinculado):
        self.vinculados.append(unVinculado)

    def agregarCondiciones(self, unaLista):
        self.condiciones.extend(unaLista)

    def armarJSONParaGesoc(self):

        #stringPaMandar = "movimiento-asociado:" + self.movimientoAsociado.getJsonFormat() \
        #                 + ",vinculados:" + self.listAJSON(self.vinculados) \
        #                 + ",criterio:" + self.criterio.getJsonFormat() \
        #                 + ",condiciones:" + self.listAJSON(self.condiciones)

        #data_set = {"movimiento-asociado": self.movimientoAsociado, "vinculados": self.vinculados,
        #            "criterio": self.criterio, "condiciones": self.condiciones}
        #json_dump = json.dumps(data_set)

        data={}
        data['movimiento-asociado'] = self.movimientoAsociado.getCodigo()
        data['vinculados'] = list(map(lambda x:x.getCodigo(),self.vinculados))
        data['criterio'] = str(self.criterio.__class__.__name__)
        data['condiciones'] = str(list(map(lambda x:str(x.__class__.__name__),self.condiciones)))
        json_data = json.dumps(data)

        print(json_data)

        return json_data

    def listAJSON(self,list):
        retorno = "["
        x = 0

        if(len(list)>0):
            retorno += list[0].getJsonFormat()
            del list[0]

            for i in list:
                retorno += ","
                retorno += list[x].getJsonFormat()
                x += 1

        return retorno + "]"



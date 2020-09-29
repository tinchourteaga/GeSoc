from flask import Flask, request, jsonify, json


class Vinculacion:
    # La juega de ingreso o de egreso (segun convenga)
    movimientoAsociado = ""

    # La juega de egresos o de ingresos(segun convenga)
    vinculados = []

    criterio = ""

    condiciones = []

    def __init__(self, unMovimientoAsociado, unCriterio):
        self.criterio = unCriterio
        self.movimientoAsociado = unMovimientoAsociado

    def agregarVinculado(self, unVinculado):
        self.vinculados.append(unVinculado)

    def agregarCondicion(self, unaCondicion):
        self.condiciones.append(unaCondicion)

    def armarJSONParaGesoc(self):
        data_set = {"movimiento-asociado": self.movimientoAsociado, "vinculados": self.vinculados,
                    "criterio": self.criterio, "condiciones": self.condiciones}
        json_dump = json.dumps(data_set)
        print(json_dump)
        return json_dump

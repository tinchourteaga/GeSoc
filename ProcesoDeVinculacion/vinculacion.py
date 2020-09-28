from flask import jsonify

from condicion import PeriodoAceptacion
from criterio import OrdenValorPrimeroIngreso, OrdenValorPrimeroEgreso
from fecha import Fecha
from importe import Importe

import json

class Vinculacion:
    ingresos=[]
    egresos=[]
    criterios=[]
    periodoMax = 0   #cantidad de dias posterior al ingreso donde vale asociarlo con el egreso.

    def __init__(self,jsonInput):
        #Recibo el json con todo el dataset

        if jsonInput is None:
            return jsonify("El JSON recibido no es valido")
        else:
            self.inicializarIngresos(jsonInput.json["ingresos"])
            self.inicializarEgresos(jsonInput.json["egresos"])
            self.inicializarCondiciones(jsonInput.json["condiciones"])
            self.inicializarCriterios(jsonInput.json["criterios"])



    def getEgresos(self):
        return self.egresos
    def getIngresos(self):
        return self.ingresos
    def getCriterios(self):
        return self.criterios
    def getCondicion(self):
        return self.periodoMax

    def addIngreso(self,ingreso):
        self.ingresos.append(ingreso)
    def addEgreso(self,egreso):
        self.egresos.append(egreso)
    def addCriterio(self,criterio):
        self.criterios.append(criterio)

    def inicializarIngresos(self,ingresos):
        # Cargo ingresos

        for ingreso in ingresos:
            fecha = Fecha.toString(ingreso.get("fecha"))
            temp = Importe(ingreso.get("ingreso"), fecha, ingreso.get("valor"))
            print("ingreso: ", temp.fecha)
            self.addIngreso(temp)

    def inicializarEgresos(self, egresos):
        # Cargo egresos
        for egreso in egresos:
            fecha = Fecha.toString(egreso.get("fecha"))
            temp = Importe(egreso.get("egreso"), fecha, egreso.get("valor"))
#            print("egreso: ", temp.fecha)
            self.addEgreso(temp)

    def inicializarCondiciones(self, condiciones):
        # Cargo Periodo de aceptacion
        for condicion in condiciones:
            if condicion.get("nombreCondicion") == "PeriodoAceptacion":
                temp = PeriodoAceptacion(condicion.get('parametros'))
            self.addCondicion(temp)

    def inicializarCriterios(self,criterios):
        #Cargo Criterio
        for criterio in criterios:
            if criterio == "OrdenValorPrimeroIngreso":
                temp = OrdenValorPrimeroIngreso()
            elif criterio == "OrdenValorPrimeroEgreso":
                temp = OrdenValorPrimeroEgreso()
            self.addCriterio(temp)


    #def asociar(self):



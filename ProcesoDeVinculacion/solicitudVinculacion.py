from flask import jsonify

from condicion import PeriodoAceptacion, Condicion
from criterio import OrdenValorPrimerIngreso, OrdenValorPrimerEgreso, Criterio, OrdenFechaPrimerEgreso
from fecha import Fecha
from importe import Importe
from vinculacion import Vinculacion


class SolicitudVinculacion:
    ingresos : Importe = []
    egresos : Importe = []
    criterios: Criterio = []
    condiciones: Condicion = []
    vinculaciones : Vinculacion = []

    #cantidad de dias posterior al ingreso donde vale asociarlo con el egreso.

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
    def getCondiciones(self):
        return self.condiciones

    def addIngreso(self,ingreso):
        self.ingresos.append(ingreso)
    def addEgreso(self,egreso):
        self.egresos.append(egreso)
    def addCriterio(self,criterio):
        self.criterios.append(criterio)
    def addCondicion(self,condicion):
        self.condiciones.append(condicion)
    def addVinculacion(self,vinculacion):
        self.vinculaciones.append(vinculacion)
        vinculacion.agregarCondiciones(self.condiciones)

    def inicializarIngresos(self,ingresos):
        # Cargo ingresos

        for ingreso in ingresos:
            fecha = Fecha.toString(ingreso.get("fecha"))
            temp = Importe(ingreso.get("ingreso"), fecha, ingreso.get("valor"))
#            print("ingreso: ", temp.fecha)
            self.addIngreso(temp)

    def inicializarEgresos(self, egresos):
        # Cargo egresos
        for egreso in egresos:
            fecha = Fecha.toString(egreso.get("fecha"))
            temp = Importe(egreso.get("egreso"), fecha, egreso.get("valor"))
#            print("egreso: ", temp.fecha)
            self.addEgreso(temp)

    def inicializarCondiciones(self, condiciones):
        # Cargo egresos
        for condicion in condiciones:
            temp=""
            if condicion.get("nombreCondicion") == "PeriodoAceptacion":
                temp = PeriodoAceptacion(condicion.get('parametros'))
            self.agregarCondicion(temp)

    def agregarCondicion(self,condicion):
        self.condiciones.append(condicion)

    def inicializarCriterios(self, criterios):
        # Cargo egresos
        for criterio in criterios:
            if criterio == "OrdenValorPrimeroIngreso":
                temp = OrdenValorPrimerIngreso()
            elif criterio == "OrdenValorPrimeroEgreso":
                temp = OrdenValorPrimerEgreso()
            elif criterio == "OrdenFechaPrimerEgreso":
                temp = OrdenFechaPrimerEgreso()

            self.agregarCriterio(temp)

    def agregarCriterio(self,criterio):
        self.criterios.append(criterio)


    def solicitarVinculacion(self):

        for unCriterio in self.criterios:
            for unIngreso in self.ingresos:
                # listaFiltrada = [unEgreso for unEgreso in self.egresos if self.cumpleTodasLasCondiciones(unIngreso,unEgreso)]
                listaFiltrada = list(filter(lambda x:self.cumpleTodasLasCondiciones(unIngreso,x),self.egresos))
                listaDeBools = list(map(lambda y:y.getEstoyVinculado()==0,listaFiltrada))
                if(all(listaDeBools) and len(listaDeBools) != 0):
                    self.addVinculacion(unCriterio.aplicar(unIngreso, listaFiltrada))

    def cumpleTodasLasCondiciones(self, ingreso: Importe, egreso: Importe):
        i=0

        if(egreso.getEstoyVinculado()==1):
            return False

        while i < len(self.condiciones):
            result= self.condiciones[i].cumple(ingreso,egreso)
            if not result:
                break;
            i += 1
        return result

    def parseoCompletoJSONs(self):
        listaDeJSONs = []
        for unaVinculacion in self.vinculaciones:
            listaDeJSONs.append(unaVinculacion.armarJSONParaGesoc())

        return listaDeJSONs


    #def aplicar(self):
    #    result = self.criterios.aplicar(self.ingresos,self.egresos,self.condiciones)
    #   return result


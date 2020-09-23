from importe import Importe
import json

class Vinculacion:
    ingresos=[]
    egresos=[]
    criterios=[]
    periodoMax = 0   #cantidad de dias posterior al ingreso donde vale asociarlo con el egreso.

    def __init__(self,jsonInput):
        #data = jsonInput.json()
        #Recibo el jsono con todo el dataset
        data = json.loads(jsonInput)

        #Cargo ingresos
        for ingreso in data['ingresos']:
            temp = Importe(ingreso[0],ingreso[1],ingreso[2])
            print("ingreso: ", temp.fecha)
            self.addIngreso(temp)

        # Cargo egresos
        for egreso in data['egresos']:
            temp = Importe(egreso[0],egreso[1],egreso[2])
            print("egreso: ", temp.fecha)
            self.addEgreso(temp)
        #Cargo Criterio
        #n criterio aplicar patron creacional, para dar el comportamiento del criterio recibido y guardar instancia.
        self.criterios=data['criterios']
        #Cargo Periodo de aceptacion
        self.periodoMax=data['condicion']


    def getEgresos(self):
        return self.egresos
    def getIngresos(self):
        return self.ingresos
    def getCondicion(self):
        return self.periodoMax

    def addIngreso(self,ingreso):
        self.ingresos.append(ingreso)
    def addEgreso(self,egreso):
        self.egresos.append(egreso)

    #def asociar(self):



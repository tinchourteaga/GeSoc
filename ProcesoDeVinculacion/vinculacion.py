from importe import Importe
import json

class Vinculacion:
    ingresos=[]
    egresos=[]
    criterio=""
    periodoAceptacion=""   #cantidad de dias posterior al ingreso donde vale asociarlo con el egreso.


    def __init__(self,jsonInput):
        #data = jsonInput.json()
        #Recibo el jsono con todo el dataset
        data = json.loads(jsonInput)

        #Cargo ingresos
        for ingreso in data['ingresos']:
            temp = Importe(ingreso[0],ingreso[1],ingreso[2])
            print("ingreso: ", temp.fecha)
            self.addIngresos(temp)

        # Cargo egresos
        for egreso in data['egresos']:
            temp = Importe(egreso[0],egreso[1],egreso[2])
            print("egreso: ", temp.fecha)
            self.addEgresos(temp)
        #Cargo Criterio
        #n criterio aplicar patron creacional, para dar el comportamiento del criterio recibido y guardar instancia.
        self.criterio=data['criterio']
        #Cargo Periodo de aceptacion
        self.periodoAceptacion=data['periodo']



    def getEgresos(self):
        return self.egresos
    def getIngresos(self):
        return self.ingresos

    def addIngresos(self,ingreso):
        self.ingresos.append(ingreso)
    def addEgresos(self,egreso):
        self.egresos.append(egreso)

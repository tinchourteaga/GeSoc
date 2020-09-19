
from importe import Importe
from vinculacion import Vinculacion

import json


#Criterios: "OVPE" , "AVPI", "Mix=["OVPE","OVPI"]"

x = '{ "ingresos" :[ [ "0001", "04/03/2020", "15000" ], [ "0002", "05/04/2020", "16000" ], [ "0003", "06/07/2020", "17000" ] ] , "egresos" :[[ "0010", "06/04/2020", "1000" ]] , "criterio" : "OVPE" , "periodo" : 7}'
y = json.loads(x)



asociar=Vinculacion(x)
for ingreso in asociar.getIngresos():
    print(ingreso.fecha)



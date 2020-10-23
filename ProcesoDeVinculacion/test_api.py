import requests
from flask import json

from condicion import PeriodoAceptacion

x = '{ "ingresos" :[ [ "0001", "04/03/2020", "15000" ], [ "0002", "05/04/2020", "16000" ], [ "0003", "06/07/2020", "17000" ] ] , "egresos" :[[ "0010", "06/04/2020", "1000" ]] , "criterios" : [ "OVPE" ] , "condicion" : 7 }'
x = '{ "ingresos" :[ {"codigo": "0001", "fecha": "04/03/2020","valor": "15000" }, {"codigo": "0002", "fecha": "05/04/2020", "valor": "16000" }, {"codigo": "0003", "fecha": "06/07/2020","valor": "17000" } ], "egresos" :[{"codigo": "0010", "fecha": "06/04/2020", "valor": "1000" }] , "criterios" : [ "OVPE" ] , "condicion" : [{"condicion": "periodo", "parametros": [7] } ] }'
y = json.loads(x)

periodo=PeriodoAceptacion([7])
print(periodo.getValor())

#res = requests.post('http://localhost:5000/api/add_message/1234', json=x)
#if res.ok:
#    print (res.json())
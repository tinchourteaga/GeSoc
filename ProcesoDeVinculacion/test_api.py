import requests

x = '{ "ingresos" :[ [ "0001", "04/03/2020", "15000" ], [ "0002", "05/04/2020", "16000" ], [ "0003", "06/07/2020", "17000" ] ] , "egresos" :[[ "0010", "06/04/2020", "1000" ]] , "criterios" : [ "OVPE" ] , "condicion" : 7 }'
#y = json.loads(x)

res = requests.post('http://localhost:5000/api/add_message/1234', json=x)
if res.ok:
    print (res.json())
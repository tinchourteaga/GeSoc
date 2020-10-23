#  import json
from flask import Flask, request, jsonify

app = Flask(__name__)


lista_egresos = [
    {
        'egreso': 'Egreso 2',
        'codigo': 2,
        'fecha': '28-08-2020',
        'importe': 10000
    }
]

lista_ingresos = [
    {
        'ingreso': 'INGRESO 520',
        'codigo': 520,
        'fecha': '28-08-2020',
        'importe': 55000,
        'fecha_desde_egreso': '15-05-2020',
        'fecha_hasta_egreso': '19-09-2020'
    }
]


@app.route('/api_proceso_vinculacion/egresos')
def mostrar_egresos():
    return jsonify(lista_egresos), 200


@app.route('/api_proceso_vinculacion/egresos', methods=["POST"])
def obtener_egresos():
    new_egreso = {
        "egreso": request.json["egreso"],
        "codigo": request.json["codigo"],
        "fecha": request.json["fecha"],
        "importe": request.json["importe"]
    }

    lista_egresos.append(new_egreso)
    return jsonify(lista_egresos), 200


@app.route('/api_proceso_vinculacion/ingresos')
def mostrar_ingresos():
    return jsonify(lista_ingresos), 200


@app.route('/api_proceso_vinculacion/ingresos', methods=["POST"])
def obtener_ingresos():
    new_ingreso = {
        "ingreso": request.json["ingreso"],
        "codigo": request.json["codigo"],
        "fecha": request.json["fecha"],
        "importe": request.json["importe"],
        "fecha_desde_egreso": request.json["fecha_desde_egreso"],
        "fecha_hasta_egreso": request.json["fecha_hasta_egreso"]
    }

    lista_ingresos.append(new_ingreso)
    return jsonify(lista_ingresos), 200


if __name__ == "__main__":
    app.run(port=5000)

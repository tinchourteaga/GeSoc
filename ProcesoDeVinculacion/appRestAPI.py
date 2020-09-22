

from flask import Flask, request, jsonify

from vinculacion import Vinculacion


class appRestAPI:
    app = Flask(__name__)

    def __init__(self):
        self.app.run(port=5000)

    @app.route('/')
    def index():
        return "Hello, World!"

    @app.route('/api_proceso_vinculacion')
    def mostrar():
        return "hola", 200

    @app.route('/api/add_message/<uuid>', methods=['POST'])
    def add_message(uuid):
        content = request.get_json(silent=True)
        print(content) # Do your processing
        #content["mytext"] = "Respuesta"
        asociar = Vinculacion(content)
        print(content)  # Do your processing
        return content
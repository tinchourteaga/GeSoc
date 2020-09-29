from datetime import datetime

from flask import json

from valor import Valor


class Importe:
    codigo=0
    fecha=""
    importe=0
    estoyVinculado=0

    def __init__(self,codigo,fecha,importe):
        self.codigo=codigo
        self.fecha=datetime.strptime(fecha, "%d/%m/%Y")
        self.importe=Valor.getImporte(importe)

    def getValorImporte(self):
        return self.importe

    def setValor(self,unValor):
        self.importe = unValor

    def getEstoyVinculado(self):
        return self.estoyVinculado

    def setEstoyVinculado(self,unBool):
        self.estoyVinculado = unBool

    def getCodigo(self):
        return self.codigo

    def getFecha(self):
        return self.fecha

    def esImporteAplicable(self, importe):
        if self.importe >= importe:
            print("aplicable")
            return True
        else:
            print("NOaplicable")
            return False

    def RestarImporte(self,importe):
        self.importe=self.importe - importe

    def getJsonFormat(self):
        data={}
        data[str(self.__class__.__name__)] = self.codigo
        json_data = json.dumps(data)
        return json_data

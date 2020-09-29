from datetime import datetime

from valor import Valor


class Importe:
    codigo=0
    fecha=""
    importe=0

    def __init__(self,codigo,fecha,importe):
        self.codigo=codigo
        self.fecha=datetime.strptime(fecha, "%d/%m/%Y")
        self.importe=Valor.getImporte(importe)

    def getValor(self):
        return self.importe

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
        return "{" + str(self.__class__.__name__) + ":{" + str(self.codigo) + "}}"

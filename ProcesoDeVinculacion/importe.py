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
            return True
        else:
            return False

    def RestarImporte(self,importe):
        self.importe=self.importe - importe



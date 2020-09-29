
import abc
from vinculacion import Vinculacion


class Criterio(metaclass=abc.ABCMeta):
    @abc.abstractmethod
    def aplicar(self,ingreso, egresosYaFiltrados):
        pass


class OrdenValorPrimerEgreso(Criterio):

    valorDelIngreso = 0
    vinculacion = ""
    egresosAux = []

    def aplicar(self,ingreso, egresosYaFiltrados):
        self.valorDelIngreso = ingreso.getValorImporte()
        self.vinculacion = Vinculacion(ingreso,self)
        egresosYaFiltrados.sort(key=lambda x: x.getValorImporte())


        for unEgreso in egresosYaFiltrados:
            if(self.egresosAux.count(unEgreso) == 0):
                self.ordenPorValorPrimerEgreso(unEgreso)
        #list(egresosYaFiltrados - self.vinculacion.egresos)

        return self.vinculacion


    def ordenPorValorPrimerEgreso(self,unEgreso):
            if self.valorDelIngreso >= unEgreso.getValorImporte() and unEgreso.getEstoyVinculado() == 0:
                self.valorDelIngreso -= unEgreso.getValorImporte()
                self.vinculacion.agregarVinculado(unEgreso)
                self.addEgresoAux(unEgreso)
                unEgreso.setEstoyVinculado(1)

    def addEgresoAux(self, unEgreso):
        self.egresosAux.append(unEgreso)

    def getJsonFormat(self):
        return str(self.__class__.__name__)


class OrdenFechaPrimerEgreso(Criterio):

    valorDelIngreso = ""
    vinculacion = ""
    egresosAux = []

    def aplicar(self,ingreso, egresosYaFiltrados):
        self.valorDelIngreso = ingreso.getValorImporte()
        self.vinculacion = Vinculacion(ingreso,self)
        egresosYaFiltrados.sort(key=lambda x: x.getFecha())
        for unEgreso in egresosYaFiltrados:
            if(self.egresosAux.count(unEgreso) != 0):
                self.ordenPorFechaPrimerEgreso(unEgreso)

        return self.vinculacion

    def ordenPorFechaPrimerEgreso(self,unEgreso):
            if(self.valorDelIngreso >= unEgreso.getValorImporte()):
                self.valorDelIngreso -= unEgreso.getValorImporte()
                self.vinculacion.agregarVinculado(unEgreso)
                self.addEgresoAux(unEgreso)

    def addEgresoAux(self, unEgreso):
        self.egresosAux.append(unEgreso)

    def getJsonFormat(self):
        return str(self.__class__.__name__)

class OrdenValorPrimerIngreso(Criterio):

    valorDelEgreso = ""
    vinculacion = ""
    ingresosAux = []

    def aplicar(self,egreso, ingresos):
        self.valorDelEgreso = egreso.getValorImporte()
        self.vinculacion = Vinculacion(egreso,self)
        ingresos.sort(key=lambda x: x.getValorImporte())
        for unIngreso in ingresos:
            if(self.ingresosAux.count(unIngreso) != 0):
                self.ordenPorValorPrimerIngreso(unIngreso)
        #list(egresosYaFiltrados - self.vinculacion.egresos)

        return self.vinculacion

    def ordenPorValorPrimerIngreso(self,unIngreso):
            if(self.valorDelEgreso >= unIngreso.getValorImporte()):
                self.valorDelEgreso -= unIngreso.getValorImporte()
                self.vinculacion.agregarVinculado(unIngreso)
                self.addIngresoAux(unIngreso)

    def addIngresoAux(self, unIngreso):
        self.ingresosAux.append(unIngreso)

    def getJsonFormat(self):
        return str(self.__class__.__name__)



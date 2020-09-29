
import abc
from vinculacion import Vinculacion


class Criterio(metaclass=abc.ABCMeta):
    @abc.abstractmethod
    def aplicar(self,ingreso, egresosYaFiltrados):
        pass


class OrdenValorPrimerEgreso(Criterio):

    valorDelIngreso = ""
    vinculacion = ""

    def aplicar(self,ingreso, egresosYaFiltrados):
        self.valorDelIngreso = ingreso.getValor()
        self.vinculacion = Vinculacion(ingreso,self)
        egresosYaFiltrados.sort(key=lambda x: x.getValor())
        for unEgreso in egresosYaFiltrados:
            self.ordenPorValorPrimerEgreso(unEgreso)
        #list(egresosYaFiltrados - self.vinculacion.egresos)

        return self.vinculacion


    def ordenPorValorPrimerEgreso(self,unEgreso):
        if self.valorDelIngreso >= unEgreso.getValor():
            self.valorDelIngreso -= unEgreso.getValor()
            self.vinculacion.agregarVinculado(unEgreso)


class OrdenFechaPrimerEgreso(Criterio):

    valorDelIngreso = ""
    vinculacion = ""

    def aplicar(self,ingreso, egresosYaFiltrados):
        self.valorDelIngreso = ingreso.getValor()
        self.vinculacion = Vinculacion(ingreso,self)
        egresosYaFiltrados.sort(key=lambda x: x.getFecha())
        for unEgreso in egresosYaFiltrados:
            self.ordenPorFechaPrimerEgreso(unEgreso)

        return self.vinculacion

    def ordenPorFechaPrimerEgreso(self,unEgreso):
        if(self.valorDelIngreso >= unEgreso.getValor()):
            self.valorDelIngreso -= unEgreso.getValor()
            self.vinculacion.agregarVinculado(unEgreso)


class OrdenValorPrimerIngreso(Criterio):

    valorDelEgreso = ""
    vinculacion = ""

    def aplicar(self,egreso, ingresos):
        self.valorDelEgreso = egreso.getValor()
        self.vinculacion = Vinculacion(egreso,self)
        ingresos.sort(key=lambda x: x.getValor())
        for unIngreso in ingresos:
            self.ordenPorValorPrimerIngreso(unIngreso)
        #list(egresosYaFiltrados - self.vinculacion.egresos)

        return self.vinculacion

    def ordenPorValorPrimerIngreso(self,unIngreso):
        if(self.valorDelEgreso >= unIngreso.getValor()):
            self.valorDelEgreso -= unIngreso.getValor()
            self.vinculacion.agregarVinculado(unIngreso)






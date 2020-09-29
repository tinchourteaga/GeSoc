
class Fecha:
    def toString(fechaDic):
        day = fechaDic.get("day")
        month = fechaDic.get("month")
        year = fechaDic.get("year")
        fecha = str(day) + "/" + str(month) + "/" + str(year)
        return fecha
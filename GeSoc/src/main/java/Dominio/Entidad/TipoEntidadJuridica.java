package Dominio.Entidad;

public abstract class TipoEntidadJuridica {
    protected String razonSocial;
    protected String cuit;
    protected String codigoPostal;
    protected String codigoDeInscripcion;
    protected Sector actividad;
    protected Integer cantidadPersonal;
    protected Float promedioVentasAnuales;

    //GETTERS Y SETTERS
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public String getCodigoDeInscripcion() {
        return codigoDeInscripcion;
    }
    public void setCodigoDeInscripcion(String codigoDeInscripcion) {
        this.codigoDeInscripcion = codigoDeInscripcion;
    }
    public Sector getActividad() {
        return actividad;
    }
    public void setActividad(Sector actividad) {
        this.actividad = actividad;
    }
    public Integer getCantidadPersonal() {
        return cantidadPersonal;
    }
    public void setCantidadPersonal(Integer cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }
    public Float getPromedioVentasAnuales() {
        return promedioVentasAnuales;
    }
    public void setPromedioVentasAnuales(Float promedioVentasAnuales) {this.promedioVentasAnuales = promedioVentasAnuales; }
}

package API.DTOs;

public class ConversionDTO {
    private MonedaDTO monedaActual;
    private MonedaDTO monedaAConvertir;
    private double ratio;
    private double ratioMercadoPago;

    public ConversionDTO(MonedaDTO monedaActual, MonedaDTO monedaAConvertir, double ratio, double ratioMercadoPago) {
        this.monedaActual = monedaActual;
        this.monedaAConvertir = monedaAConvertir;
        this.ratio = ratio;
        this.ratioMercadoPago = ratioMercadoPago;
    }

    public MonedaDTO getMonedaActual() {
        return monedaActual;
    }

    public MonedaDTO getMonedaAConvertir() {
        return monedaAConvertir;
    }

    public double getRatio() {
        return ratio;
    }

    public double getRatioMercadoPago() {
        return ratioMercadoPago;
    }
}

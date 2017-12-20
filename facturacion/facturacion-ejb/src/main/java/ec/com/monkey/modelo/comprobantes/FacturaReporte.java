package ec.com.monkey.modelo.comprobantes;

import java.util.List;

public class FacturaReporte {
	private Factura factura;
	private String detalle1;
	private String detalle2;
	private String detalle3;
	private List<DetallesAdicionalesReporte> detallesAdiciones;
	private List<InformacionAdicional> infoAdicional;
	private List<FormasPago> formasPago;
	private List<TotalesComprobante> totalesComprobante;

	public FacturaReporte(Factura factura) {
		this.factura = factura;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public String getDetalle1() {
		return this.detalle1;
	}

	public void setDetalle1(String detalle1) {
		this.detalle1 = detalle1;
	}

	public String getDetalle2() {
		return this.detalle2;
	}

	public void setDetalle2(String detalle2) {
		this.detalle2 = detalle2;
	}

	public String getDetalle3() {
		return this.detalle3;
	}

	public void setDetalle3(String detalle3) {
		this.detalle3 = detalle3;
	}

	public List<DetallesAdicionalesReporte> getDetallesAdiciones() {
		return detallesAdiciones;
	}

	public void setDetallesAdiciones(List<DetallesAdicionalesReporte> detallesAdiciones) {
		this.detallesAdiciones = detallesAdiciones;
	}

	public List<InformacionAdicional> getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(List<InformacionAdicional> infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public List<FormasPago> getFormasPago() {
		return formasPago;
	}

	public void setFormasPago(List<FormasPago> formasPago) {
		this.formasPago = formasPago;
	}

	public List<TotalesComprobante> getTotalesComprobante() {
		return totalesComprobante;
	}

	public void setTotalesComprobante(List<TotalesComprobante> totalesComprobante) {
		this.totalesComprobante = totalesComprobante;
	}

}

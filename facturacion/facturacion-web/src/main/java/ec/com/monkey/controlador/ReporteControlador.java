package ec.com.monkey.controlador;

import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ec.com.monkey.constantes.FacturacionConstantes;
import ec.com.monkey.enumeradores.TipoImpuestoFacturacionEnum;
import ec.com.monkey.enumeradores.TipoImpuestoIvaEnum;
import ec.com.monkey.modelo.DetalleFactura;
import ec.com.monkey.modelo.comprobantes.DetallesAdicionalesReporte;
import ec.com.monkey.modelo.comprobantes.Factura;
import ec.com.monkey.modelo.comprobantes.Factura.InfoFactura;
import ec.com.monkey.modelo.comprobantes.FacturaReporte;
import ec.com.monkey.modelo.comprobantes.FormasPago;
import ec.com.monkey.modelo.comprobantes.InfoTributaria;
import ec.com.monkey.modelo.comprobantes.InformacionAdicional;
import ec.com.monkey.modelo.comprobantes.IvaDiferenteCeroReporte;
import ec.com.monkey.modelo.comprobantes.TotalComprobante;
import ec.com.monkey.modelo.comprobantes.TotalesComprobante;
import ec.com.monkey.servlet.ReportServlet;
import ec.com.monkey.util.Util;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named(value = "reporteControlador")
@ViewScoped
public class ReporteControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1243206382330344589L;

	public void generarReporteFactura(List<DetalleFactura> detalle, Factura factura) {
		try {
			FacturaReporte facturaReporte = new FacturaReporte(factura);
			facturaReporte.setDetallesAdiciones(generarDetallesAdicionales(factura));
			JRDataSource dataSource = new JRBeanCollectionDataSource(facturaReporte.getDetallesAdiciones());
			InfoTributaria infoTributaria = factura.getInfoTributaria();
			InfoFactura infoFactura = factura.getInfoFactura();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("RUC", infoTributaria.getRuc());
			param.put("CLAVE_ACC", infoTributaria.getClaveAcceso());
			param.put("RAZON_SOCIAL", infoTributaria.getRazonSocial());
			param.put("DIR_MATRIZ", infoTributaria.getDirMatriz());
			param.put("LOGO", new FileInputStream(Util.obtenerRutaFacturas()+"logo.png"));
//			param.put("LOGO", null);
			param.put("SUBREPORT_DIR", "/report/");
			param.put("SUBREPORT_PAGOS", "resources/reportes/");
			param.put("SUBREPORT_TOTALES", "resources/reportes/");
			param.put("TIPO_EMISION", "NORMAL");
			param.put("NUM_AUT", "");
			param.put("FECHA_AUT", null);
			param.put("MARCA_AGUA", null);
			param.put("NUM_FACT", infoTributaria.getEstab() + "-" + infoTributaria.getPtoEmi() + "-"
					+ infoTributaria.getSecuencial());
			param.put("AMBIENTE", "PRUEBAS");
			param.put("NOM_COMERCIAL", infoTributaria.getNombreComercial());

			BigDecimal TotalSinSubsidio = BigDecimal.ZERO;
			BigDecimal TotalSinDescuento = BigDecimal.ZERO;
			BigDecimal TotalSubsidio = BigDecimal.ZERO;
			param.put("DIR_SUCURSAL", infoFactura.getDirEstablecimiento());
			param.put("CONT_ESPECIAL", infoFactura.getContribuyenteEspecial());
			param.put("LLEVA_CONTABILIDAD", infoFactura.getObligadoContabilidad());
			param.put("RS_COMPRADOR", infoFactura.getRazonSocialComprador());
			param.put("RUC_COMPRADOR", infoFactura.getIdentificacionComprador());
			param.put("DIRECCION_CLIENTE", infoFactura.getIDireccionComprador());
			param.put("FECHA_EMISION", infoFactura.getFechaEmision());
			param.put("GUIA", infoFactura.getGuiaRemision());
			param.put("TOTAL_SIN_SUBSIDIO", TotalSinSubsidio.setScale(2, RoundingMode.UP));
			param.put("AHORRO_POR_SUBSIDIO", TotalSubsidio.setScale(2, RoundingMode.UP));

			Map<String, Object> subReportes = new HashMap<String, Object>();
			subReportes.put("totalesComprobante", "totalesComprobante");
			subReportes.put("facturaInfoAdicional", "facturaInfoAdicional");
			subReportes.put("facturaFormasPago", "facturaFormasPago");

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			request.getSession().setAttribute(ReportServlet.OBJETO_REPORTE, param);
			request.getSession().setAttribute(ReportServlet.OBJETO_SUB_REPORTE, subReportes);
			request.getSession().setAttribute(ReportServlet.OBJETO_DATASOURCE, dataSource);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<DetallesAdicionalesReporte> generarDetallesAdicionales(Factura factura) throws ClassNotFoundException {
		List<DetallesAdicionalesReporte> detallesAdiciones = new ArrayList();
		for (Factura.Detalles.Detalle det : factura.getDetalles().getDetalle()) {
			DetallesAdicionalesReporte detAd = new DetallesAdicionalesReporte();
			detAd.setCodigoPrincipal(det.getCodigoPrincipal());
			detAd.setCodigoAuxiliar(det.getCodigoAuxiliar());
			detAd.setDescripcion(det.getDescripcion());
			detAd.setCantidad(det.getCantidad().toString());
			detAd.setPrecioTotalSinImpuesto(det.getPrecioTotalSinImpuesto().toString());
			detAd.setPrecioUnitario(det.getPrecioUnitario());
			detAd.setPrecioSinSubsidio(det.getPrecioSinSubsidio());
			if (det.getDescuento() != null) {
				detAd.setDescuento(det.getDescuento().toString());
			}
			detAd.setInfoAdicional(getInfoAdicional(factura));
			detAd.setFormasPago(getFormasPago(factura));
			detAd.setTotalesComprobante(getTotalesComprobante(factura));
			detallesAdiciones.add(detAd);
		}
		return detallesAdiciones;
	}

	public List<InformacionAdicional> getInfoAdicional(Factura factura) {
		List<InformacionAdicional> infoAdicional = new ArrayList();

		if (factura.getInfoAdicional() != null) {
			if ((factura.getInfoAdicional().getCampoAdicional() != null)
					&& (!factura.getInfoAdicional().getCampoAdicional().isEmpty())) {
				for (Factura.InfoAdicional.CampoAdicional ca : factura.getInfoAdicional().getCampoAdicional()) {
					infoAdicional.add(new InformacionAdicional(ca.getValue(), ca.getNombre()));
				}
			}
		}
		return infoAdicional;
	}

	public List<FormasPago> getFormasPago(Factura factura) {
		List<FormasPago> formasPago = new ArrayList();
		if (factura.getInfoFactura().getPagos() != null) {
			if ((factura.getInfoFactura().getPagos().getPagos() != null)
					&& (!factura.getInfoFactura().getPagos().getPagos().isEmpty())) {
				for (Factura.InfoFactura.Pago.DetallePago pa : factura.getInfoFactura().getPagos().getPagos()) {
					formasPago.add(new FormasPago(
							FacturacionConstantes.TEXTO_FORMA_PAGO_SIN_UTILIZACION_DEL_SISTEMA_FINANCIERO,
							pa.getTotal().setScale(2).toString()));
				}
			}
		}
		return formasPago;
	}

	public List<TotalesComprobante> getTotalesComprobante(Factura factura) throws ClassNotFoundException {
		List<TotalesComprobante> totalesComprobante = new ArrayList();
		BigDecimal importeTotal = BigDecimal.ZERO.setScale(2);
		BigDecimal compensaciones = BigDecimal.ZERO.setScale(2);
		TotalComprobante tc = getTotales(factura.getInfoFactura());
		for (IvaDiferenteCeroReporte iva : tc.getIvaDistintoCero()) {
			totalesComprobante
					.add(new TotalesComprobante("SUBTOTAL " + iva.getTarifa() + "%", iva.getSubtotal(), false));
		}
		totalesComprobante.add(new TotalesComprobante("SUBTOTAL IVA 0%", tc.getSubtotal0(), false));
		totalesComprobante.add(new TotalesComprobante("SUBTOTAL NO OBJETO IVA", tc.getSubtotalNoSujetoIva(), false));
		totalesComprobante.add(new TotalesComprobante("SUBTOTAL EXENTO IVA", tc.getSubtotalExentoIVA(), false));
		totalesComprobante.add(new TotalesComprobante("SUBTOTAL SIN IMPUESTOS",
				factura.getInfoFactura().getTotalSinImpuestos(), false));
		totalesComprobante
				.add(new TotalesComprobante("DESCUENTO", factura.getInfoFactura().getTotalDescuento(), false));
		totalesComprobante.add(new TotalesComprobante("ICE", tc.getTotalIce(), false));
		for (IvaDiferenteCeroReporte iva : tc.getIvaDistintoCero()) {
			totalesComprobante.add(new TotalesComprobante("IVA " + iva.getTarifa() + "%", iva.getValor(), false));
		}
		totalesComprobante.add(new TotalesComprobante("IRBPNR", tc.getTotalIRBPNR(), false));
		totalesComprobante.add(new TotalesComprobante("PROPINA", factura.getInfoFactura().getPropina(), false));
		if (factura.getInfoFactura().getCompensaciones() != null) {
			for (Factura.InfoFactura.compensacion.detalleCompensaciones compensacion : factura.getInfoFactura()
					.getCompensaciones().getCompensaciones()) {
				compensaciones = compensaciones.add(compensacion.getValor());
			}
			importeTotal = factura.getInfoFactura().getImporteTotal().add(compensaciones);
		}
		if (!compensaciones.equals(BigDecimal.ZERO.setScale(2))) {
			totalesComprobante.add(new TotalesComprobante("VALOR TOTAL", importeTotal, false));
			totalesComprobante
					.add(new TotalesComprobante("VALOR A PAGAR", factura.getInfoFactura().getImporteTotal(), false));
		} else {
			totalesComprobante
					.add(new TotalesComprobante("VALOR TOTAL", factura.getInfoFactura().getImporteTotal(), false));
		}
		return totalesComprobante;
	}

	private TotalComprobante getTotales(Factura.InfoFactura infoFactura) {
		List<IvaDiferenteCeroReporte> ivaDiferenteCero = new ArrayList();

		BigDecimal totalIva = new BigDecimal(0.0D);
		BigDecimal totalIva0 = new BigDecimal(0.0D);
		BigDecimal totalExentoIVA = new BigDecimal(0.0D);

		BigDecimal totalICE = new BigDecimal(0.0D);
		BigDecimal totalIRBPNR = new BigDecimal(0.0D);
		BigDecimal totalSinImpuesto = new BigDecimal(0.0D);
		TotalComprobante tc = new TotalComprobante();
		for (Factura.InfoFactura.TotalConImpuestos.TotalImpuesto ti : infoFactura.getTotalConImpuestos()
				.getTotalImpuesto()) {
			Integer cod = new Integer(ti.getCodigo());
			if ((TipoImpuestoFacturacionEnum.IVA.getCode() == cod.intValue()) && (ti.getValor().doubleValue() > 0.0D)) {
				String codigoPorcentaje = FacturacionConstantes.TARIFA_IVA_12;
				IvaDiferenteCeroReporte iva = new IvaDiferenteCeroReporte(ti.getBaseImponible(), codigoPorcentaje,
						ti.getValor());
				ivaDiferenteCero.add(iva);
			}
			if ((TipoImpuestoFacturacionEnum.IVA.getCode() == cod.intValue())
					&& (TipoImpuestoIvaEnum.IVA_VENTA_0.getCode().equals(ti.getCodigoPorcentaje()))) {
				totalIva0 = totalIva0.add(ti.getBaseImponible());
			}
			if ((TipoImpuestoFacturacionEnum.IVA.getCode() == cod.intValue())
					&& (TipoImpuestoIvaEnum.IVA_NO_OBJETO.getCode().equals(ti.getCodigoPorcentaje()))) {
				totalSinImpuesto = totalSinImpuesto.add(ti.getBaseImponible());
			}
			if ((TipoImpuestoFacturacionEnum.IVA.getCode() == cod.intValue())
					&& (TipoImpuestoIvaEnum.IVA_EXCENTO.getCode().equals(ti.getCodigoPorcentaje()))) {
				totalExentoIVA = totalExentoIVA.add(ti.getBaseImponible());
			}
			if (TipoImpuestoFacturacionEnum.ICE.getCode() == cod.intValue()) {
				totalICE = totalICE.add(ti.getValor());
			}
			if (TipoImpuestoFacturacionEnum.IRBPNR.getCode() == cod.intValue()) {
				totalIRBPNR = totalIRBPNR.add(ti.getValor());
			}
		}
		if (ivaDiferenteCero.isEmpty()) {
			ivaDiferenteCero.add(LlenaIvaDiferenteCero());
		}
		tc.setIvaDistintoCero(ivaDiferenteCero);
		tc.setSubtotal0(totalIva0);
		tc.setTotalIce(totalICE);
		tc.setSubtotal(totalIva0.add(totalIva).add(totalSinImpuesto).add(totalExentoIVA));
		tc.setSubtotalExentoIVA(totalExentoIVA);
		tc.setTotalIRBPNR(totalIRBPNR);
		tc.setSubtotalNoSujetoIva(totalSinImpuesto);
		return tc;
	}

	private IvaDiferenteCeroReporte LlenaIvaDiferenteCero() {
		BigDecimal valor = BigDecimal.ZERO.setScale(2);
		String porcentajeIva = "12";
		return new IvaDiferenteCeroReporte(valor, porcentajeIva, valor);
	}

}

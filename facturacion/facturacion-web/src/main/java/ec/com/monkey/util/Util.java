package ec.com.monkey.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ec.com.monkey.constantes.Constantes;
import ec.com.monkey.constantes.FacturacionConstantes;
import ec.com.monkey.modelo.Cliente;
import ec.com.monkey.modelo.DetalleFactura;
import ec.com.monkey.modelo.Productos;
import ec.com.monkey.modelo.comprobantes.Factura;
import ec.com.monkey.modelo.comprobantes.Factura.InfoFactura;
import ec.com.monkey.modelo.comprobantes.Impuesto;
import ec.com.monkey.modelo.comprobantes.InfoTributaria;
import ec.com.monkey.servicios.local.FacturaServiceLocal;

public class Util {

	public static String obtenerNumeroVerificador(String cadena) {
		char[] vector = cadena.toCharArray();
		char[] vectorInvertido = invertir(vector);
		vectorInvertido.toString();
		int rutSumado = obtenerSumaDigitos(vectorInvertido);
		int resto = rutSumado % 11;
		String digito = String.valueOf(11 - resto);
		if (digito.equals("11"))
			digito = "0";
		if (digito.equals("10"))
			digito = "1";
		return digito;
	}

	public static char[] invertir(char[] vector) {
		char[] invertir_int = new char[vector.length];
		int maximo = vector.length;

		for (int i = 0; i < vector.length; i++) {
			invertir_int[maximo - 1] = vector[i];
			maximo--;
		}
		return invertir_int;
	}

	public static Integer obtenerSumaDigitos(char[] array) {
		int rutSumado = 0;
		int a = 2;
		for (int i = 0; i < array.length; i++) {
			int valor = Integer.parseInt("" + array[i]) * a;
			rutSumado += valor;
			if (a == 7) {
				a = 1;
			}
			a++;
		}
		return rutSumado;
	}
	
	public static String generarNumeroFactura(FacturaServiceLocal facturaService)
	{
		Integer numeroActual = facturaService.obtenerNumeroFactura();
		numeroActual += 1;
		String formatString = String.format("%%0%dd", 9);
		String numero = String.format(formatString, numeroActual);
		return numero;
	}
	
	public static Factura generarXML(ec.com.monkey.modelo.Factura documento,  List<DetalleFactura> detalleFactura)
	{
		try {
			Factura facturaLlena = generarComprobante(documento, detalleFactura);
			crearDirectorio(Constantes.FOLDER_FACTURAS);
			String ruta = obtenerRutaFacturas();
			ruta += documento.getNumeroFc()+".xml";
			crearArchivoXml2(ruta, facturaLlena);
			return facturaLlena;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Factura generarComprobante(ec.com.monkey.modelo.Factura documento, List<DetalleFactura> detalleFactura){
		
		Factura factura = null;
		try {
			factura = new Factura();
			llenarComprobante(documento, factura);
			Factura.Detalles detalles = generarDetalle(detalleFactura);
			Factura.InfoAdicional informacion = generarInformacionAdicional(documento.getCodigoCliente());
			factura.setInfoAdicional(informacion);
	        if (detalles != null) {
	          factura.setDetalles(detalles);
	        }
	        factura.setVersion("1.0.0");
	        factura.setId("comprobante");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factura;
	}
	
	public static void llenarComprobante(ec.com.monkey.modelo.Factura documento, Factura factura)
	{
		SimpleDateFormat dt1 = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = dt1.format(new Date());
		String fechaActual = dt2.format(new Date());
		String codigoNumerico = generarNumerico();
		InfoTributaria infoTributaria = new InfoTributaria();
	    infoTributaria.setSecuencial(documento.getNumeroFc());
	    infoTributaria.setAmbiente(FacturacionConstantes.AMBIENTE_PRUEBAS);
	    infoTributaria.setTipoEmision(FacturacionConstantes.EMISION_NORMAL);
	    infoTributaria.setRazonSocial(documento.getCodigoEm().getNombreEm());
	    infoTributaria.setRuc(documento.getCodigoEm().getRuc());
	    infoTributaria.setCodDoc(FacturacionConstantes.TIPO_DOCUMENTO_FACTURA);
	    infoTributaria.setEstab(FacturacionConstantes.ESTABLECIMIENTO);
	    infoTributaria.setPtoEmi(FacturacionConstantes.PUNTO_EMISION);
	    infoTributaria.setDirMatriz(documento.getCodigoEm().getDireccion());
	    String claveAcceso = fecha + infoTributaria.getCodDoc() + infoTributaria.getRuc() + infoTributaria.getAmbiente() + infoTributaria.getEstab() + infoTributaria.getPtoEmi() + infoTributaria.getSecuencial() + codigoNumerico + infoTributaria.getTipoEmision();
		String digitoVerificador = obtenerNumeroVerificador(claveAcceso);
		claveAcceso += digitoVerificador;
		infoTributaria.setClaveAcceso(claveAcceso);
		factura.setInfoTributaria(infoTributaria);
		
		//Datos info factura
		
	    InfoFactura infoFactura = new InfoFactura();
	    infoFactura.setFechaEmision(fechaActual);
	    infoFactura.setDirEstablecimiento(documento.getCodigoEm().getDireccion());
	    
	    infoFactura.setIdentificacionComprador(documento.getCodigoCliente().getIdentificacionCl());
	    infoFactura.setTipoIdentificacionComprador(documento.getCodigoCliente().getCodigoTi().getNombreTi().toUpperCase().equals("RUC") ? "04" : "05");
	    infoFactura.setRazonSocialComprador(documento.getCodigoCliente().getNombreCl() + " " + documento.getCodigoCliente().getNombreCl());
	    infoFactura.setTotalSinImpuestos(new BigDecimal(documento.getSubtotalFc()));
	    infoFactura.setTotalDescuento(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
	    infoFactura.setPropina(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
	    infoFactura.setImporteTotal(new BigDecimal(documento.getTotalFc()).setScale(2, RoundingMode.HALF_UP));
	    infoFactura.setMoneda("DOLAR");
	    infoFactura.setTotalConImpuestos(generaTotalesImpuesto(documento));
	    infoFactura.setPagos(generarPagos(documento.getTotalFc()));
	    infoFactura.setObligadoContabilidad("SI");
	    factura.setInfoFactura(infoFactura);
	  }
	
	public static String generarNumerico()
	{
		String numerico = "";
		for(int i = 0; i<8;i++)
		{
			int numero = (int) (Math.random() * 9) + 1;
			numerico += numero;
		}
		return numerico;
	}
	
	private static Factura.InfoFactura.TotalConImpuestos generaTotalesImpuesto(ec.com.monkey.modelo.Factura documento) {
		Factura.InfoFactura.TotalConImpuestos respuesta = new Factura.InfoFactura.TotalConImpuestos();
		Factura.InfoFactura.TotalConImpuestos.TotalImpuesto item = new Factura.InfoFactura.TotalConImpuestos.TotalImpuesto();
		item = obtieneTotal(documento);
		respuesta.getTotalImpuesto().add(item);
		return respuesta;
	}
	
	
	private static Factura.InfoFactura.TotalConImpuestos.TotalImpuesto obtieneTotal (ec.com.monkey.modelo.Factura documento) {
		Factura.InfoFactura.TotalConImpuestos.TotalImpuesto impuesto = new Factura.InfoFactura.TotalConImpuestos.TotalImpuesto();
		BigDecimal baseImponible = new BigDecimal(documento.getSubtotalFc());

		BigDecimal total = new BigDecimal(documento.getValorImpFc());

		impuesto.setCodigo(FacturacionConstantes.IMPUESTO_IVA);
		impuesto.setCodigoPorcentaje(FacturacionConstantes.TARIFA_IVA_12);

		impuesto.setTarifa(new BigDecimal(12).setScale(0));
		impuesto.setBaseImponible(baseImponible.setScale(2, RoundingMode.HALF_UP));
		impuesto.setValor(total.setScale(2, RoundingMode.HALF_UP));
		return impuesto;
	}
	
	private static Factura.InfoFactura.Pago generarPagos(Double total) {
		Factura.InfoFactura.Pago pagos = new Factura.InfoFactura.Pago();
		Factura.InfoFactura.Pago.DetallePago pago = new Factura.InfoFactura.Pago.DetallePago();
		pago.setFormaPago(FacturacionConstantes.FORMA_PAGO_SIN_UTILIZACION_DEL_SISTEMA_FINANCIERO);
		pago.setTotal(BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP));
		pagos.getPagos().add(pago);
		return pagos;
	}
	
	private static Factura.Detalles generarDetalle(List<DetalleFactura> listaDetalle)
	  {
	    Factura.Detalles resultado = new Factura.Detalles();
	    for (DetalleFactura df : listaDetalle) {
	      try
	      {
	        Factura.Detalles.Detalle detalle = new Factura.Detalles.Detalle();
	          detalle.setCodigoPrincipal(df.getCodigoPr().getCodigoPr().toString());
	          detalle.setCodigoAuxiliar(df.getCodigoPr().getCodigoRefPr());
	        
	        detalle.setDescripcion(df.getCodigoPr().getDescripcionPr());
	        detalle.setCantidad(new BigDecimal(df.getCantidadDf()).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros());
	        detalle.setPrecioUnitario(new BigDecimal(df.getValorDf()));
	        detalle.setDescuento(new BigDecimal(0));
	        detalle.setPrecioTotalSinImpuesto(new BigDecimal(df.getCantidadDf() * df.getValorDf()).setScale(2, RoundingMode.HALF_UP));
	        
	        detalle.setImpuestos(obtenerImpuestosProducto(df.getCodigoPr(), df.getCantidadDf()));
	        
	        resultado.getDetalle().add(detalle);
	      }
	      catch (Exception ex)
	      {
	        ex.printStackTrace();
	      }
	    }
	    return resultado;
	  }
	
	private static Factura.InfoAdicional generarInformacionAdicional(Cliente cliente)
	  {
	    Factura.InfoAdicional info = new Factura.InfoAdicional();
	    for (int i = 0; i < 2; i++)
	    {
	      Factura.InfoAdicional.CampoAdicional detalle = new Factura.InfoAdicional.CampoAdicional();
	      if(i==0)
	      {
	    	  detalle.setNombre("Telefono");
		      detalle.setValue(cliente.getTelefonoCl());
	      }
	      else
	      {
	    	  detalle.setNombre("Email");
		      detalle.setValue(cliente.getCorreoCl());
	      }
	      
	      
	      info.getCampoAdicional().add(detalle);
	    }
	    return info;
	  }
	
	private static Factura.Detalles.Detalle.Impuestos obtenerImpuestosProducto(Productos producto, int cantidad) {
		Factura.Detalles.Detalle.Impuestos result = new Factura.Detalles.Detalle.Impuestos();

		Impuesto i = new Impuesto();
		i.setCodigo(FacturacionConstantes.IMPUESTO_IVA);
		i.setCodigoPorcentaje(FacturacionConstantes.TARIFA_IVA_12);
		i.setTarifa(new BigDecimal(12));
		i.setBaseImponible(new BigDecimal(producto.getPrecioPr()*cantidad).setScale(2, RoundingMode.HALF_UP));
		i.setValor((new BigDecimal(producto.getPrecioPr() * cantidad))
				.multiply((new BigDecimal(12)).divide(BigDecimal.valueOf(100L))).setScale(2, RoundingMode.HALF_UP));
		result.getImpuesto().add(i);

		return result;
	}
	
	 public static String crearArchivoXml2(String pathArchivo, Object objetoModelo)
	  {
	    String respuestaCreacion = null;
	    if (objetoModelo != null) {
	      try
	      {
	        respuestaCreacion = realizaMarshal(objetoModelo, pathArchivo);
	      }
	      catch (Exception ex)
	      {
	        ex.printStackTrace();
	      }
	    } else {
	      respuestaCreacion = "Ingrese los campos obligatorios del comprobante";
	    }
	    return respuestaCreacion;
	  }
	 
	 
	 private static String realizaMarshal(Object comprobante, String pathArchivo)
	  {
	    String respuesta = null;
	    if ((comprobante instanceof Factura)) {
	      respuesta = Java2XML.marshalFactura((Factura)comprobante, pathArchivo);
	    } 
	    return respuesta;
	  }
	 
		public static void crearDirectorio(String nombre) {
			if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
				File folder = new File(
						System.getProperty("jboss.home.dir") + "\\" + Constantes.FOLDER_SERVIDOR + "\\" + nombre);
				if (!folder.exists()) {
					folder.mkdir();
				}
			} else {
				File folder = new File(
						System.getProperty("jboss.home.dir") + "/" + Constantes.FOLDER_SERVIDOR + "/" + nombre);
				if (!folder.exists()) {
					folder.mkdir();
				}
			}
		}
		
		public static String obtenerRutaFacturas()
		{
			String ruta;
			if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
				ruta = System.getProperty("jboss.home.dir") + "\\" + Constantes.FOLDER_SERVIDOR + "\\" + Constantes.FOLDER_FACTURAS+"\\";
			} else {
				ruta = System.getProperty("jboss.home.dir") + "/" + Constantes.FOLDER_SERVIDOR + "/" + Constantes.FOLDER_FACTURAS+"/";
			}
			return ruta;
		}

}

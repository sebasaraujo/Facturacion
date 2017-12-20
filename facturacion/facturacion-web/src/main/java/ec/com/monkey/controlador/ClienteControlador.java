package ec.com.monkey.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.enumeradores.TipoImpuestoEnum;
import ec.com.monkey.modelo.Cliente;
import ec.com.monkey.modelo.DetalleFactura;
import ec.com.monkey.modelo.Factura;
import ec.com.monkey.modelo.ImpuestoFactura;
import ec.com.monkey.modelo.Productos;
import ec.com.monkey.modelo.TipoIdentificacion;
import ec.com.monkey.servicios.local.ClienteServiceLocal;
import ec.com.monkey.servicios.local.DetalleFacturaServiceLocal;
import ec.com.monkey.servicios.local.FacturaServiceLocal;
import ec.com.monkey.servicios.local.ImpuestoFacturaServiceLocal;
import ec.com.monkey.servicios.local.ProductosServiceLocal;
import ec.com.monkey.servicios.local.TipoFacturaServiceLocal;
import ec.com.monkey.servicios.local.TipoIdentificacionServiceLocal;
import ec.com.monkey.util.Util;

@Named(value = "clienteControlador")
@ViewScoped
public class ClienteControlador extends BaseControlador implements Serializable{

	private static final long serialVersionUID = 5902297392607064428L;
	private Cliente cliente;
	private Integer codigoTipoIdentificacion;
	private Boolean mostrarCliente = Boolean.TRUE;
	private List<TipoIdentificacion> listaTipoIdentificacion = new ArrayList<>();
	private List<DetalleFactura> listaDetalleFactura = new ArrayList<>();
	private Productos producto = new Productos();
	private Integer cantidad = 1;
	private Double subtotal = 0.0;
	private Double impuestos = 0.0;
	private Double total = 0.0;
	private Factura factura;
	private ec.com.monkey.modelo.comprobantes.Factura facturaXml;
	
	@EJB
	private ClienteServiceLocal clienteService;
	@EJB
	private TipoIdentificacionServiceLocal tipoIdentificacionService;
	@EJB
	private ProductosServiceLocal productosService;
	@EJB
	private ImpuestoFacturaServiceLocal impuestoFacturaService;
	@EJB
	private FacturaServiceLocal facturaService;
	@EJB
	private TipoFacturaServiceLocal tipoFacturaService;
	@EJB
	private DetalleFacturaServiceLocal detalleFacturaService;
	@Inject
	private SessionControlador sessionControlador;
	
	@PostConstruct
	public void init()
	{
		if (sessionControlador.isLogueoCorrecto()) {
			cliente = new Cliente();
			mostrarCliente = Boolean.TRUE;
			listaTipoIdentificacion = tipoIdentificacionService.obtenerTodos(); 
		} else {
			redireccionarPagina("/faces/paginas/login.xhtml");
		}
	}
	
	public void buscarCliente(AjaxBehaviorEvent event)
	{
		if(cliente.getIdentificacionCl() != null)
		{
			Cliente clienteTemp = clienteService.buscarClienteXIdentificacion(cliente.getIdentificacionCl());
			if(clienteTemp != null)
			{
				cliente = clienteTemp;
				codigoTipoIdentificacion = cliente.getCodigoTi().getCodigoTi();
			}
			else
			{
				mostrarCliente = Boolean.FALSE;
				agregarMensajeInformacion("No existe un cliente registrado con el numero de cedula "+cliente.getIdentificacionCl(), "");
			}
		}
		else
		{
			agregarMensajeError("Debe ingresar un numero de cedula", "");
		}
	}
	
	public void buscarProducto(AjaxBehaviorEvent event)
	{
		if(producto.getCodigoRefPr() != null)
		{
			Productos productoTemp = productosService.obtenerProductoXCodigoRef(producto.getCodigoRefPr());
			if(productoTemp != null)
			{
				producto = productoTemp;
				cantidad = 1;
			}
			else
			{
				agregarMensajeInformacion("No existe ningun producto con el codigo: "+producto.getCodigoRefPr(), "");
			}
		}
		else
		{
			agregarMensajeError("Debe ingresar un codigo de producto", "");
		}
	}
	
	public void guardarCliente()
	{
		try {
			cliente.setCodigoCi(sessionControlador.getLocal().getCodigoCi());
			cliente.setEstadoCl(EstadoEnum.ACTIVO.getValor());
			cliente.setCodigoTi(tipoIdentificacionService.findById(codigoTipoIdentificacion));
			cliente.setUsuarioActCl(1);
			cliente.setFechaActCl(new Date());
			mostrarCliente = Boolean.TRUE;
			clienteService.crear(cliente);
			agregarMensajeInformacion("Cliente ingresado correctamente", "");
		} catch (Exception e) {
			agregarMensajeError(e);
		}
		
		
	}
	
	public void agregarProducto()
	{
		DetalleFactura df = new DetalleFactura();
		df.setCodigoPr(producto);
		df.setCantidadDf(cantidad);
		df.setValorDf(producto.getPrecioPr());
		df.setValorImpuestoDf(obtenerImpuesto(producto.getPrecioPr(), cantidad));
		df.setValorTotalDf((df.getValorDf() * df.getCantidadDf()) + df.getValorImpuestoDf());
		listaDetalleFactura.add(df);
		producto = new Productos();
		cantidad = 1;
		calcularValores();
	}
	
	public Double obtenerImpuesto(Double precio, Integer cantidad)
	{
		Double impuesto = 0.0;
		List<ImpuestoFactura> impuestoFactura = impuestoFacturaService.obtenerImpuestosXTipoFactura(1);
		for(ImpuestoFactura ifa : impuestoFactura)
		{
			if(ifa.getCodigoIp().getTipoImpuesto().equals(TipoImpuestoEnum.PORCENTUAL.getValor()))
			{
				impuesto += ((precio * cantidad) * ifa.getCodigoIp().getValorIp()) / 100;
			}
			else
			{
				impuesto += ifa.getCodigoIp().getValorIp();
			}
		}
		
		return impuesto;
	}
	
	public void calcularValores()
	{
		subtotal = 0.0;
		impuestos = 0.0;
		total = 0.0;
		for(DetalleFactura df : listaDetalleFactura)
		{
			subtotal += df.getCantidadDf() * df.getCodigoPr().getPrecioPr();
			impuestos += df.getValorImpuestoDf();
		}
		total = subtotal + impuestos;
	}
	
	public void eliminarProducto(DetalleFactura detalleFactura)
	{
		listaDetalleFactura.remove(detalleFactura);
		calcularValores();
		agregarMensajeInformacion("Producto eliminado correctamente", "");
	}
	
	public void guardarCompra()
	{
		try {
			factura = new Factura();
			factura.setCodigoCliente(cliente);
			factura.setCodigoEm(sessionControlador.getLocal().getCodigoEm());
			factura.setCodigoEmp(sessionControlador.getEmpleado());
			factura.setCodigoTf(tipoFacturaService.findById(1));
			factura.setEstadoFc(EstadoEnum.ACTIVO.getValor());
			factura.setFechaActFc(new Date());
			factura.setFechaFc(new Date());
			factura.setNumeroFc(Util.generarNumeroFactura(facturaService));
			factura.setSubtotalFc(subtotal);
			factura.setTotalFc(total);
			factura.setValorImpFc(impuestos);
			factura.setUsuarioActFc(1);
			factura.setCodigoCi(sessionControlador.getLocal().getCodigoCi());
			facturaService.crear(factura);
			for(DetalleFactura df : listaDetalleFactura)
			{
				df.setCodigoFc(factura);
				df.setFechaActDf(new Date());
				df.setUsuarioActDf(1);
				detalleFacturaService.crear(df);
			}
			facturaXml = Util.generarXML(factura,listaDetalleFactura);
			agregarMensajeInformacion("Factura realizada correctamente", "");
		} catch (Exception e) {
			
		}
		
	}
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigoTipoIdentificacion() {
		return codigoTipoIdentificacion;
	}

	public void setCodigoTipoIdentificacion(Integer codigoTipoIdentificacion) {
		this.codigoTipoIdentificacion = codigoTipoIdentificacion;
	}

	public Boolean getMostrarCliente() {
		return mostrarCliente;
	}

	public void setMostrarCliente(Boolean mostrarCliente) {
		this.mostrarCliente = mostrarCliente;
	}

	public List<TipoIdentificacion> getListaTipoIdentificacion() {
		return listaTipoIdentificacion;
	}

	public void setListaTipoIdentificacion(List<TipoIdentificacion> listaTipoIdentificacion) {
		this.listaTipoIdentificacion = listaTipoIdentificacion;
	}

	public List<DetalleFactura> getListaDetalleFactura() {
		return listaDetalleFactura;
	}

	public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
		this.listaDetalleFactura = listaDetalleFactura;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public ec.com.monkey.modelo.comprobantes.Factura getFacturaXml() {
		return facturaXml;
	}

	public void setFacturaXml(ec.com.monkey.modelo.comprobantes.Factura facturaXml) {
		this.facturaXml = facturaXml;
	}
	
	

}

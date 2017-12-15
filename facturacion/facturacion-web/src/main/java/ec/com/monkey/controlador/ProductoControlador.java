package ec.com.monkey.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.modelo.Productos;
import ec.com.monkey.modelo.TipoProducto;
import ec.com.monkey.servicios.local.ProductosServiceLocal;
import ec.com.monkey.servicios.local.TipoProductoServiceLocal;

@Named(value = "productoControlador")
@ViewScoped
public class ProductoControlador extends BaseControlador implements Serializable{

	private static final long serialVersionUID = 2197588676183419433L;
	private Productos producto = new Productos();
	private List<TipoProducto> listaTipoProductos = new ArrayList<>();
	private Integer codigoTipoProducto;
	private List<Productos> listaProductos = new ArrayList<>();
	
	@EJB
	private TipoProductoServiceLocal tipoProductoService;
	@EJB
	private ProductosServiceLocal productosService;
	@Inject
	private SessionControlador sessionControlador;
	
	@PostConstruct
	public void init()
	{
		if(sessionControlador.isLogueoCorrecto())
		{
			listaTipoProductos = tipoProductoService.obtenerTodos();
			listaProductos = productosService.obtenerTodos();
		}
		else
		{
			redireccionarPagina("/faces/paginas/login.xhtml");
		}
		
	}


	public void guardarProducto()
	{
		try {
			System.out.println("guardar");
			System.out.println("producto nombre: "+producto.getNombrePr());
			System.out.println("codigo tp: "+codigoTipoProducto);
			producto.setCodigoTp(tipoProductoService.findById(codigoTipoProducto));
			producto.setEstadoPr(EstadoEnum.ACTIVO.getValor());
			producto.setFechaActPr(new Date());
			producto.setUsuarioActPr(1);
			productosService.crear(producto);
			producto = new Productos();
			listaProductos = productosService.obtenerTodos();
			agregarMensajeInformacion("Producto creado correctamente", "");
		} catch (Exception e) {
			agregarMensajeError(e);
		}
		
	}
	
	public void onRowEdit(RowEditEvent event) {
        try {
			Productos productoTemp = (Productos) event.getObject();
			productosService.actualizar(productoTemp);
			agregarMensajeInformacion("Producto actualizado correctamente", "");
		} catch (Exception e) {
			agregarMensajeError(e);
		}
    }
     
    public void onRowCancel(RowEditEvent event) {
    }
	
	public Productos getProducto() {
		return producto;
	}


	public void setProducto(Productos producto) {
		this.producto = producto;
	}


	public List<TipoProducto> getListaTipoProductos() {
		return listaTipoProductos;
	}


	public void setListaTipoProductos(List<TipoProducto> listaTipoProductos) {
		this.listaTipoProductos = listaTipoProductos;
	}


	public Integer getCodigoTipoProducto() {
		return codigoTipoProducto;
	}


	public void setCodigoTipoProducto(Integer codigoTipoProducto) {
		this.codigoTipoProducto = codigoTipoProducto;
	}


	public List<Productos> getListaProductos() {
		return listaProductos;
	}


	public void setListaProductos(List<Productos> listaProductos) {
		this.listaProductos = listaProductos;
	}

}

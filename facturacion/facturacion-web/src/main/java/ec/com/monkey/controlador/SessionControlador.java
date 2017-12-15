package ec.com.monkey.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.modelo.Empleado;
import ec.com.monkey.modelo.Local;
import ec.com.monkey.modelo.Usuario;
import ec.com.monkey.servicios.local.EmpleadoServiceLocal;
import ec.com.monkey.servicios.local.LocalServiceLocal;
import ec.com.monkey.servicios.local.UsuarioServiceLocal;

@Named
@SessionScoped
public class SessionControlador extends BaseControlador implements Serializable{
	
	private static final long serialVersionUID = 4424154438468275671L;

	private Local local;
	private Empleado empleado;
	private String nombreUsuario;
	private boolean logueoCorrecto;
	private String identificacionUsuario;
	
	@EJB
	private LocalServiceLocal localService;
	@EJB
	private EmpleadoServiceLocal empleadoService;
	@EJB
	private UsuarioServiceLocal usuarioServiceLocal;
	
	@PostConstruct
	public void init()
	{
		if(logueoCorrecto)
		{
			System.out.println("logueo: "+logueoCorrecto);
			Usuario usuario = usuarioServiceLocal.obtenerPorIdentificacion(identificacionUsuario, EstadoEnum.ACTIVO.getValor());
			local = localService.obtenerLocalXCodigo(1);
			empleado = usuario.getCodigoEmp();
			System.out.println("nombre local: "+empleado.getCodigoLo());
		}
		else
		{
			redireccionarPagina("/faces/paginas/login.xhtml");
		}
		
	}
	
	public void asaignar()
	{
		System.out.println("logueo: "+logueoCorrecto);
		Usuario usuario = usuarioServiceLocal.obtenerPorIdentificacion(identificacionUsuario, EstadoEnum.ACTIVO.getValor());
		local = localService.obtenerLocalXCodigo(1);
		empleado = usuario.getCodigoEmp();
		System.out.println("nombre local: "+empleado.getCodigoLo());
	}
	
	public void cerrarSesion() {
		logueoCorrecto = false;
		getHttpRequest().getSession().invalidate();
		redireccionarPagina("/faces/paginas/login.xhtml");
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public boolean isLogueoCorrecto() {
		return logueoCorrecto;
	}

	public void setLogueoCorrecto(boolean logueoCorrecto) {
		this.logueoCorrecto = logueoCorrecto;
	}

	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

}

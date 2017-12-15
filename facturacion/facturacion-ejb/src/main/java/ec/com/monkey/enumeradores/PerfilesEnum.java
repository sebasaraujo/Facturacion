package ec.com.monkey.enumeradores;

public enum PerfilesEnum {
	ADMINISTRADOR(1),
	EMPLEADO(2);
	
	private Integer codigo;
	
	private PerfilesEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
}

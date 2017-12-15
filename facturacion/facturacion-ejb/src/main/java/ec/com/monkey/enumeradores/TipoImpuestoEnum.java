package ec.com.monkey.enumeradores;

public enum TipoImpuestoEnum {
	PORCENTUAL(1),
	FIJO(2);
	
	private Integer valor;

	private TipoImpuestoEnum(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	
	
}

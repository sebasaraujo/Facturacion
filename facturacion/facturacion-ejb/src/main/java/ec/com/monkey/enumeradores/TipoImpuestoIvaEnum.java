package ec.com.monkey.enumeradores;

public enum TipoImpuestoIvaEnum {
	IVA_VENTA_0("0"), IVA_VENTA_12("2"), IVA_NO_OBJETO("6"), IVA_EXCENTO("7");

	private String code;

	private TipoImpuestoIvaEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}

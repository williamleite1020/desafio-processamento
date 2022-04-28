package br.com.processamento.enums;

public enum EnumTipoRegistro {
	
	VENDEDOR(1l , "Vendedor", "001"),
	CLIENTE(2l, "Cliente", "002"),
	VENDA(3l, "Venda", "003");
	
	private Long numeroIdentificador;
	private String nome;
	private String identificador;
	
	private EnumTipoRegistro(Long numeroIdentificador, String nome, String identificador) {
		this.numeroIdentificador = numeroIdentificador;
		this.nome = nome;
		this.identificador = identificador;
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public Long getNumerIdentificador() {
		return numeroIdentificador;
	}

	public String getNome() {
		return nome;
	}

}

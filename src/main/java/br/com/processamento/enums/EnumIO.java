package br.com.processamento.enums;

public enum EnumIO {
	
	IN("in"),
	OUT("out");
	
	private String name;
	
	private EnumIO(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

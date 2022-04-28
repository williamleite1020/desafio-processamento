package br.com.processamento.model;

public class Cliente extends Entidade {

	private String cnpj;
	private String nome;
	private String businessArea;

	public void setId(Long id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	public Cliente(Long id, String cnpj, String nome, String businessArea) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.businessArea = businessArea;
	}
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
}

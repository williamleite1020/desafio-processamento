package br.com.processamento.model;

import java.math.BigDecimal;

public class Vendedor extends Entidade {

	private String cpf;
	private String nome;
	private BigDecimal salario;

	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	public Vendedor() {
		// TODO Auto-generated constructor stub
	}
	
	public Vendedor(Long id, String cpf, String nome, BigDecimal salario) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}
}

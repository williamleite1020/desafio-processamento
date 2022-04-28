package br.com.processamento.model;

import java.math.BigDecimal;

public class ItemVenda extends Entidade {

	private Integer quantidade;
	private BigDecimal valor;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ItemVenda(Long id, Integer quantidade, BigDecimal valor) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
	
}

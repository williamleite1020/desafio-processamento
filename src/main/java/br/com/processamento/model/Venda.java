package br.com.processamento.model;

import java.util.List;

public class Venda extends Entidade {

	private Long saleId;
	private String salesManName;
	private List<ItemVenda> listaItens;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public String getSalesManName() {
		return salesManName;
	}

	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}

	public List<ItemVenda> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemVenda> listaItens) {
		this.listaItens = listaItens;
	}

	public Venda() {
		// TODO Auto-generated constructor stub
	}
	
	public Venda(Long id, Long saleId, List<ItemVenda> listaItens, String salesManName) {
		super();
		this.id = id;
		this.saleId = saleId;
		this.salesManName = salesManName;
		this.listaItens = listaItens;
	}
}

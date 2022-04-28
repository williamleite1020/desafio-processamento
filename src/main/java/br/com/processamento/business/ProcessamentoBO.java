package br.com.processamento.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.processamento.enums.EnumTipoRegistro;
import br.com.processamento.exception.ProcessException;
import br.com.processamento.factory.RegistroFactory;
import br.com.processamento.model.Entidade;
import br.com.processamento.model.Venda;

public class ProcessamentoBO {
	
	/**
	 * Metodo reponsavel por converter os registros 
	 * @param listRecord
	 * @return
	 * @throws ProcessException
	 */
	public List<Entidade> converterRegistros (List<String> listRecord) throws ProcessException {
		List<Entidade> listaRetorno = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(listRecord)) {
			listRecord.forEach( a -> {
				if (StringUtils.isNotBlank(a))
					listaRetorno.add(RegistroFactory.criarRegistro(a));
			});
		}
		
		return listaRetorno;
	}
	
	/**
	 * Metodo reponsavel por obter uma lista com resultados do processamento
	 * @param listaResultados
	 * @return
	 */
	public List<String> obterResultados(List<Entidade> listaResultados) {
		List<String> listaRetorno = new ArrayList<>();
		
		listaRetorno.add("Quantidade de clientes no arquivo de entrada: " + obterQuantidadeParametrizado(listaResultados, EnumTipoRegistro.CLIENTE));
		listaRetorno.add("Quantidade de vendedor no arquivo de entrada: " + obterQuantidadeParametrizado(listaResultados, EnumTipoRegistro.VENDEDOR));
		listaRetorno.add("ID da venda mais cara: " + obterVendaMaisAlta(listaResultados).getSaleId().toString());
		listaRetorno.add("O pior vendedor: " + obterPiorVendedor(listaResultados).getSalesManName());
		return listaRetorno;
	}
	
	/**
	 * Metodo que obtem a quantidade de registros para uma entrada parametrizada
	 * @param listaResultados
	 * @param enumTipo
	 * @return
	 */
	private String obterQuantidadeParametrizado (List<Entidade> listaResultados, EnumTipoRegistro enumTipo) {
		Integer qtdClientes = 0;
		
		if (CollectionUtils.isNotEmpty(listaResultados))
			qtdClientes = listaResultados.stream()
			.filter(a -> a.getId().equals(Long.parseLong(enumTipo.getIdentificador())))
			.collect(Collectors.toList()).size();
		
		return qtdClientes.toString();
	}

	/**
	 * Metodo com responsabilidade de obter o ID da venda mais cara: 
	 * @param listaResultados
	 * @return
	 */
	private Venda obterVendaMaisAlta (List<Entidade> listaResultados) {
		Venda maiorVendaEfetuada = null;
		
		if (CollectionUtils.isNotEmpty(listaResultados)) {
			
			List<Entidade> listaVendasEfetuadas = 
					listaResultados.stream()
					.filter(a -> a.getId().equals(EnumTipoRegistro.VENDA.getNumerIdentificador()))
					.collect(Collectors.toList());

			BigDecimal auxMaiorValorVenda = new BigDecimal(0);
			
			for (Entidade itemRegistro : listaVendasEfetuadas) {
				Venda venda = (Venda) itemRegistro;
				BigDecimal totalValorVendas = obterTotalVendas(venda);
				
				if (auxMaiorValorVenda.compareTo(new BigDecimal(0)) == 0 || totalValorVendas.compareTo(auxMaiorValorVenda) > 0) {
					auxMaiorValorVenda = totalValorVendas;
					maiorVendaEfetuada = venda;
				}
			}
		}
		
		return maiorVendaEfetuada;
			 
	}

	/**
	 * Metodo com resposanbilidade de obter o pior vendedor: 
	 * @param listaResultados
	 * @return
	 */
	private Venda obterPiorVendedor (List<Entidade> listaResultados) {
		Venda piorVenda = null;
		
		if (CollectionUtils.isNotEmpty(listaResultados)) {
			
			List<Entidade> listaVendasEfetuadas = 
					listaResultados.stream()
					.filter(a -> a.getId().equals(EnumTipoRegistro.VENDA.getNumerIdentificador()))
					.collect(Collectors.toList());
			
			BigDecimal auxMenorValorVenda = new BigDecimal(0);
			
			for (Entidade itemRegistro : listaVendasEfetuadas) {
				Venda venda = (Venda) itemRegistro;
				BigDecimal totalValorVendas = obterTotalVendas(venda);
				
				if (auxMenorValorVenda.compareTo(new BigDecimal(0)) == 0 || totalValorVendas.compareTo(auxMenorValorVenda) < 0) {
					auxMenorValorVenda = totalValorVendas;
					piorVenda = venda;
				}
			}
		}
			
		return piorVenda;
	}
	
	/**
	 * Metodo responsavel por calcular o total de vendas 
	 * @param venda
	 * @return
	 */
	private BigDecimal obterTotalVendas(Venda venda) {
		BigDecimal totalValorVendas = new BigDecimal(0);
		venda.getListaItens().forEach(a -> totalValorVendas.add(a.getValor()));
		return totalValorVendas;
	}
}

package br.com.processamento.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.processamento.model.Entidade;
import br.com.processamento.model.ItemVenda;
import br.com.processamento.model.Venda;
import br.com.processamento.util.Constantes;

public class VendaFactory extends ARegistro implements IRegistroFactory {

	@Override
	public Entidade criarEntidade(String linha) {
		List<String> listaParametros = obterParametrosLinha(linha, Constantes.DELIMITER_CEDILHA);

		return new Venda(
				Long.parseLong(listaParametros.get(0)), 
				Long.parseLong(listaParametros.get(1)),
				converterListaItem(listaParametros.get(2)), 
				listaParametros.get(3));
	}

	private List<ItemVenda> converterListaItem(String linha) {
		String[] array = linha.split(Constantes.DELIMITER_VIRGULA);
		List<ItemVenda> listaRetorno = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			String item = array[i].replace("[", "").replace("]", "");
			List<String> listaParametros = obterParametrosLinha(item, Constantes.DELIMITER_HIFEN);

			ItemVenda dto = new ItemVenda(
					Long.parseLong(listaParametros.get(0)), 
					Integer.parseInt(listaParametros.get(1)),
					new BigDecimal(listaParametros.get(2)));

			listaRetorno.add(dto);
		}

		return listaRetorno;
	}

}

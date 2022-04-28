package br.com.processamento.factory;

import br.com.processamento.enums.EnumTipoRegistro;
import br.com.processamento.model.Entidade;

public class RegistroFactory {

	public static Entidade criarRegistro(String linha) {

		if (linha.startsWith(EnumTipoRegistro.VENDEDOR.getIdentificador())) {
			return new VendedorFactory().criarEntidade(linha);
			
		} else if (linha.startsWith(EnumTipoRegistro.CLIENTE.getIdentificador())) {
			return new ClienteFactory().criarEntidade(linha);

		} else if (linha.startsWith(EnumTipoRegistro.VENDA.getIdentificador())) {
			return new VendaFactory().criarEntidade(linha);
		
		} else {
			return null;
		}
	}
}

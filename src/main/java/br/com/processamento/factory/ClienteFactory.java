package br.com.processamento.factory;

import java.util.List;

import br.com.processamento.model.Cliente;
import br.com.processamento.model.Entidade;
import br.com.processamento.util.Constantes;

public class ClienteFactory extends ARegistro implements IRegistroFactory {
	
	@Override
	public Entidade criarEntidade(String linha) {
		List<String> listaParametros = obterParametrosLinha(linha, Constantes.DELIMITER_CEDILHA);
		 
		return new Cliente(
				Long.parseLong(listaParametros.get(0)),
				listaParametros.get(1),
				listaParametros.get(2),
				listaParametros.get(3)
				); 
	}

}

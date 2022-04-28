package br.com.processamento.factory;

import java.math.BigDecimal;
import java.util.List;

import br.com.processamento.model.Entidade;
import br.com.processamento.model.Vendedor;
import br.com.processamento.util.Constantes;

public class VendedorFactory extends ARegistro implements IRegistroFactory {
	
	@Override
	public Entidade criarEntidade(String linha) {
		List<String> listaParametros = obterParametrosLinha(linha, Constantes.DELIMITER_CEDILHA);
		 
		return new Vendedor(
				Long.parseLong(listaParametros.get(0)),
				listaParametros.get(1),
				listaParametros.get(2),
				new BigDecimal(listaParametros.get(3))
				); 
	}


}

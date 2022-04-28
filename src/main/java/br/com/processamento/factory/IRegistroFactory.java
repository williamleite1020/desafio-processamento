package br.com.processamento.factory;

import br.com.processamento.model.Entidade;

public interface IRegistroFactory {
	
	  abstract Entidade criarEntidade(String linha);

}

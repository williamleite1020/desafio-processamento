package br.com.processamento.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class ARegistro {

	protected List<String> obterParametrosLinha(String linha, String delimiter) {
		
		StringTokenizer st = new StringTokenizer(linha, delimiter);
		List<String> listaParametros = new ArrayList<>();
		
		while (st.hasMoreTokens()) {
			listaParametros.add(st.nextToken());
		}
		
		return listaParametros;
	}

}

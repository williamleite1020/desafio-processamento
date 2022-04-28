package br.com.processamento.business;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.processamento.enums.EnumTipoRegistro;
import br.com.processamento.exception.ProcessException;
import br.com.processamento.model.Cliente;
import br.com.processamento.model.Entidade;
import br.com.processamento.model.ItemVenda;
import br.com.processamento.model.Venda;
import br.com.processamento.model.Vendedor;
import junit.framework.TestCase;

public class ProcessamentoBOTest extends TestCase {

	private static ProcessamentoBO processamento;

	@Before
	public void setUp() {
		processamento = new ProcessamentoBO();
	}

	@Test
	public void testConverterRegistros() {
		try {
			List<Entidade> listaEntidades = processamento.converterRegistros(obterLinhasTeste());
			assertNotNull(listaEntidades);
		} catch (ProcessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObterResultados() {
			List<String> listaInformacoesObtidas = processamento.obterResultados(montarListaEntidade());
			assertNotNull(listaInformacoesObtidas);
			assertEquals(listaInformacoesObtidas.get(0), "Quantidade de clientes no arquivo de entrada: 1");
			assertEquals(listaInformacoesObtidas.get(1), "Quantidade de vendedor no arquivo de entrada: 1");
			assertEquals(listaInformacoesObtidas.get(2), "ID da venda mais cara: 158");
			assertEquals(listaInformacoesObtidas.get(3), "O pior vendedor: nameSales1");
	}

	private List<Entidade> montarListaEntidade () {
		Venda venda1 = new Venda(EnumTipoRegistro.VENDA.getNumerIdentificador(), 158l, Arrays.asList(
				new ItemVenda(1l, 15, new BigDecimal("1500.52")),
				new ItemVenda(2l, 2, new BigDecimal("15")),
				new ItemVenda(3l, 1, new BigDecimal("122"))
				), "nameSales1");
		Vendedor vendedor1 = new Vendedor(EnumTipoRegistro.VENDEDOR.getNumerIdentificador(), "02179558173", "William", new BigDecimal("1500.52"));
		Cliente cliente1 = new Cliente(EnumTipoRegistro.CLIENTE.getNumerIdentificador(), "02179558173111", "William", "salesBusiness");
		
		return Arrays.asList(venda1, vendedor1, cliente1);
	}
	
	private List<String> obterLinhasTeste() {
		return Arrays.asList(
				"001ç1234567891234çPedroç50000", 
				"001ç3245678865434çPauloç40000.99",
				"001ç02179158173çWilliamç9785", 
				"002ç2345675434544345çJose da SilvaçRural",
				"002ç2345675433444345çEduardo PereiraçRural", 
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
				"003ç08ç[89-34-108,2-33-1.50,3-40-0.10]çJoao");
	}
}

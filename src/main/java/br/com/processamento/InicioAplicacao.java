package br.com.processamento;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;

import br.com.processamento.business.ProcessamentoBO;
import br.com.processamento.enums.EnumIO;
import br.com.processamento.exception.ProcessException;
import br.com.processamento.model.Entidade;
import br.com.processamento.util.Constantes;
import br.com.processamento.util.FileUtil;

/**
 * Hello world!
 *
 */
public class InicioAplicacao {

	private static final List<String> LISTA_VARIAVEIS = Arrays.asList(Constantes.HOME_PATH, Constantes.USE_HOME);

	public static void main(String[] args) throws ProcessException  {
		String caminhoDiretorio = obterCaminhoDiretorio(EnumIO.IN, LISTA_VARIAVEIS);
		processarLote(Paths.get(caminhoDiretorio));
	}
	
	private static void processarLote(Path caminhoEntrada) throws ProcessException {

		try {

			WatchService watchService = FileSystems.getDefault().newWatchService();
			WatchKey watchKey = caminhoEntrada.register(
					watchService, 
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY);

			while ((watchKey = watchService.take()) != null) {

				for (WatchEvent<?> event : watchKey.pollEvents()) {
					String nomeArquivoEntrada = event.context().toString();

					if (FileUtil.validateFileExtension(nomeArquivoEntrada, Constantes.EXTENSAO_DAT)) {

						Path diretorioArquivoEntrada = FileUtil.buildDirectoryFile(
								obterCaminhoDiretorio(EnumIO.IN, LISTA_VARIAVEIS), nomeArquivoEntrada);
						
						ProcessamentoBO processamento = new ProcessamentoBO();

						List<String> listaRegistros = Files.readAllLines(diretorioArquivoEntrada);
						List<Entidade> listaRegistrosDominio = processamento.converterRegistros(listaRegistros);
						List<String> listaInformacoesObtidas = processamento.obterResultados(listaRegistrosDominio);

						String nomeArquivoSaida = FileUtil.construirNomeArquivoComExtensao(nomeArquivoEntrada, Constantes.EXTENSAO_DONE_DAT);
						Path diretorioArquivoSaida = FileUtil.buildDirectoryFile(obterCaminhoDiretorio(EnumIO.OUT, LISTA_VARIAVEIS), nomeArquivoSaida);
						FileUtil.escreverArquivoSaida(listaInformacoesObtidas, diretorioArquivoSaida);

						watchKey.reset();
					}
				}
			}

		} catch (IOException | InterruptedException e) {
			throw new ProcessException("Erro ao processar registros de entrada: " + e.getMessage());
		}
	}

	private static String obterCaminhoDiretorio(EnumIO enumIO, List<String> variable) {
		return new StringBuilder()
				.append(FileUtil.getEnvironmentVariable(variable))
				.append(File.separator)
				.append("data")
				.append(File.separator)
				.append(enumIO.getName())
				.toString();
	}

}

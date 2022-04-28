package br.com.processamento.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.processamento.exception.ProcessException;

public class FileUtil {
	
	public static boolean validateFileExtension(String nameFile, String extensionAllowed) {
		return FilenameUtils.getExtension(nameFile).equalsIgnoreCase(extensionAllowed);
	}
	
	public static String getEnvironmentVariable(List<String> listVariable) throws RuntimeException {
		String envVariable = null;
		
		for (String variable : listVariable) {
			envVariable = System.getenv(variable);
			if (StringUtils.isNoneEmpty(envVariable))
				return envVariable;
		}
		
		if (StringUtils.isEmpty(envVariable)) {
			throw new RuntimeException("Variavel de ambiente não localizada");
		}
		
		return envVariable;
	}
	
	public static boolean createFile(Path path) throws IOException {
		File file= FileUtils.createParentDirectories(path.toFile());
		return file.createNewFile();
	}
	
	public static String construirNomeArquivoComExtensao(String fileName, String extension) {
		return new StringBuilder()
				.append(FilenameUtils.getBaseName(fileName))
				.append(extension)
				.toString();
	}
	
	public static void escreverArquivoSaida(List<String> listaStr, Path outPutDiretory) throws ProcessException {
		try {
			FileUtil.createFile(outPutDiretory);
			Files.write(outPutDiretory, listaStr, StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			throw new ProcessException("Erro ao efetuar escrita de saida: " + e.getMessage());
		}
		
	}

	public static Path buildDirectoryFile(String rootDiretory, String fileName) {
		return Paths.get(new StringBuilder()
				 .append(rootDiretory)
				 .append(File.separator)
				 .append(fileName)
				 .toString());
	}
}

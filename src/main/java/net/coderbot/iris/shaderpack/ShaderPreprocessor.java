package net.coderbot.iris.shaderpack;

import net.coderbot.iris.Iris;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShaderPreprocessor {
	public static String process(Path rootPath, Path shaderPath, String source, ShaderPackConfig config) throws IOException {
		StringBuilder processed = new StringBuilder();

		List<String> lines = processInternal(rootPath, shaderPath, source, config);

		for (String line : lines) {
			processed.append(line);
			processed.append('\n');
		}

		return processed.toString();
	}

	private static List<String> processInternal(Path rootPath, Path shaderPath, String source, ShaderPackConfig config) throws IOException {
		List<String> lines = new ArrayList<>();

		// Match any valid newline sequence
		// https://stackoverflow.com/a/31060125
		for (String line : source.split("\\R")) {
			String trimmedLine = line.trim();

			if (trimmedLine.startsWith("#include ")) {
				try {
					lines.addAll(include(rootPath, shaderPath, trimmedLine, config));
				} catch (IOException e) {
					throw new IOException("Failed to read file from #include directive", e);
				}

				continue;
			}

			lines.add(line);
		}

		try {
			DefineOptionParser.processConfigOptions(lines, config);
		} catch (Exception e) {
			Iris.logger.error("Error while processing config options for file {}", shaderPath.toString());
			Iris.logger.error(e.getMessage());
		}

		return lines;
	}

	private static List<String> include(Path rootPath, Path shaderPath, String directive, ShaderPackConfig config) throws IOException {
		// Remove the "#include " part so that we just have the file path
		String target = directive.substring("#include ".length()).trim();

		// Remove quotes if they're present
		// All include directives should have quotes, but I
		if (target.startsWith("\"")) {
			target = target.substring(1);
		}

		if (target.endsWith("\"")) {
			target = target.substring(0, target.length() - 1);
		}

		Path included;

		if (target.startsWith("/")) {
			target = target.substring(1);

			included = rootPath.resolve(target);
		} else {
			included = shaderPath.getParent().resolve(target);
		}

		String source = readFile(included);

		return processInternal(rootPath, included, source, config);
	}

	private static String readFile(Path path) throws IOException {
		return Files.readString(path);
	}
}

package reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * SourceReader class reads in a text file into a string
 * 
 * @author Casey
 *
 */
// TODO: allow for multiple source files to be linked with import statements
public class SourceReader {
	private static final Logger LOGGER = Logger.getLogger(SourceReader.class.getName());
	private SourceCode src;

	public SourceReader() {
		src = new SourceCode();
	}

	public void read(String path) {
		Charset encoding = Charset.defaultCharset();
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			LOGGER.info("Completed ingesting source code");
			src.addSource(new String(encoded, encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public SourceCode getSrc() {
		return src;
	}
}

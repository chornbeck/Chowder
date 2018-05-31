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
	private String src;

	public SourceReader() {
		src = "";
	}

	public void read(String path) {
		Charset encoding = Charset.defaultCharset();
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			LOGGER.info("Completed injesting source code");
			setSrc(new String(encoded, encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setSrc(String content) {
		this.src = content;
	}

	public String getSrc() {
		return src;
	}
}

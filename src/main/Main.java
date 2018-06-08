package main;

import logger.LogManagerConfigurator;
import parser.Parser;
import reader.SourceReader;
import tokenizer.Tokenizer;

public class Main {
	public static void main(String[] args) {
		LogManagerConfigurator logMC = new LogManagerConfigurator();
		logMC.configureLogManager();

		SourceReader srcReader = new SourceReader();
		srcReader.read("./examples/test1.ch");

		Tokenizer tokenizer = new Tokenizer();
		tokenizer.tokenize(srcReader.getSrc());

		Parser parser = new Parser();
		parser.parse(tokenizer.getTokenList());

	}
}

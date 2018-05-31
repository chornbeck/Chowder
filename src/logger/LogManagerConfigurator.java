package logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class LogManagerConfigurator {

	private static final LogManager logManager = LogManager.getLogManager();
	private final String logPath = "./log.properties";

	public LogManagerConfigurator() {

	}

	public void configureLogManager() {
		try {
			logManager.readConfiguration(new FileInputStream(logPath));
		} catch (SecurityException | IOException e) {
		}
	}
}

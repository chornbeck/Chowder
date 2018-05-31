package logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

	@Override
	// [ level ][ class name ] logMsg
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder(1000);
		builder.append("[ ").append(record.getLevel()).append(" ]");
		builder.append("[ ").append(record.getSourceClassName()).append(" ] ");
		builder.append(formatMessage(record)).append("\n");
		return builder.toString();
	}
}

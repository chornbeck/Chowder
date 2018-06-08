package tokenizer;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import reader.SourceCode;
import token.CDouble;
import token.CIdentifier;
import token.CInteger;
import token.CSpecial;
import token.CString;
import token.CToken;
import token.CWhitespace;

/**
 * Tokenizer class reads in the source text and creates tokens from it
 * 
 * @author Casey
 *
 */
public class Tokenizer {
	private static final Logger LOGGER = Logger.getLogger(Tokenizer.class.getName());

	private final List<Character> specialChars = Arrays.asList('!', '#', '%', '^', '&', '*', '(', ')', '-', '+', '_', '=', '[', ']', '{', '}', ';', ':', '\'', '\"', '.', '<', '>', '/', '|');

	private SourceCode sourceCode;
	private TokenList tokenList;

	public Tokenizer() {
		tokenList = new TokenList();
		sourceCode = null;
	}

	public TokenList getTokenList() {
		return tokenList;
	}

	public void tokenize(SourceCode s) {
		sourceCode = s;
		char c;
		c = getNextChar();
		while (c != '\0') {
			if (sourceCode.hasCharsLeft()) {
				// if the char is a letter tokenize an identifier
				if (isAlpha(c)) {
					// put the letter back
					putCharBack();
					tokenizeIdentifier();

					// else if the char is a digit tokenize a numeric
				} else if (isDigit(c)) {
					// put the digit back
					putCharBack();
					tokenizeNumeric();

					// else if the char is a special character tokenize a
					// special, if its a '.' check if next char is a digit to
					// parse a numeric
				} else if (isSpecial(c)) {
					if (c == '.') {
						if (isDigit(getNextChar())) {
							// put digit back
							putCharBack();
							// put '.' back
							putCharBack();
							tokenizeNumeric();
						} else {
							// put the '.' back
							putCharBack();
							tokenizeSpecial();
						}
					} else if (c == '\"') {
						// Dont put char back, dont want leading ' " '
						tokenizeString();
					} else {
						// put special char back
						putCharBack();
						tokenizeSpecial();
					}

					// else if the char is a whitespace character tokenize a
					// whitespace
				} else if (isWhitespace(c)) {
					// put whitespace back
					putCharBack();
					tokenizeWhitespace();

					// else something back happened
				} else {
					// Some horrible error happened
				}
			}
			c = getNextChar();
		}
	}

	private void tokenizeString() {
		char c = getNextChar();
		String token = "";
		while (c != '\0') {
			if (c == '\"') {
				LOGGER.info("Created string token: " + token);
				addToken(new CString(token));
				break;
			} else {
				token += c;
			}
			c = getNextChar();
		}
	}

	private void tokenizeIdentifier() {
		char c;
		String token = "";
		while (sourceCode.currentCharLessThanLength()) {
			c = getNextChar();
			if (isAlpha(c) || isDigit(c) || c == '_') {
				token += c;
			} else {
				break;
			}
		}
		if (sourceCode.currentCharLessThanLength())
			putCharBack();
		LOGGER.info("Created identifier token: " + token);
		addToken(new CIdentifier(token));
	}

	private void tokenizeNumeric() {
		char c;
		boolean seenDecimal = false;
		String token = "";

		while (sourceCode.currentCharLessThanLength()) {
			c = getNextChar();
			if (isDigit(c)) {
				token += c;
			} else if (c == '.') {
				if (!seenDecimal) {
					token += c;
					seenDecimal = true;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (sourceCode.currentCharLessThanLength())
			putCharBack();
		if (seenDecimal) {
			LOGGER.info("Created double token: " + token);
			addToken(new CDouble(token));
		} else {
			LOGGER.info("Created integer token: " + token);
			addToken(new CInteger(token));
		}
	}

	private void tokenizeSpecial() {
		char c;
		String token = "";
		c = getNextChar();
		token += c;
		LOGGER.info("Created special token: " + token);
		addToken(new CSpecial(token));
	}

	private void tokenizeWhitespace() {
		char c;
		String token = "";
		c = getNextChar();
		if (c == '\n') {
			token += c;
			LOGGER.info("Created whitespace token");
			addToken(new CWhitespace(token));
		}
	}

	private char getNextChar() {
		return sourceCode.getNextChar();
	}

	private void putCharBack() {
		sourceCode.putCharBack();
	}

	private void addToken(CToken t) {
		tokenList.addToken(t);
	}

	private boolean isAlpha(char c) {
		return Character.isLetter(c);
	}

	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	private boolean isWhitespace(char c) {
		return Character.isWhitespace(c);
	}

	private boolean isSpecial(char c) {
		return specialChars.contains(Character.valueOf(c));
	}
}

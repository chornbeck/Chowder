package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import statement.Statement;
import statement.AssignmentStatement;
import token.CIdentifier;
import token.CSpecial;
import token.CToken;
import token.CWhitespace;
import tokenizer.TokenList;
import tokenizer.Tokenizer;

/**
 * Parser class parses the tokenlist from tokenizer and creates statements out of them
 * 
 * @author Casey
 *
 */
public class Parser {
	private static final Logger LOGGER = Logger.getLogger(Tokenizer.class.getName());
	private final List<String> keywords = Arrays.asList("import", "class", "public", "private", "if", "else", "while", "do", "for", "in", "break", "continue", "return", "new", "true", "false", "null" );

	private TokenList tokenList;
	private List<Statement> statementList;
	public Parser() {
		tokenList = null;
		statementList = new ArrayList<>();
	}

	public void parse(TokenList tl) {
		tokenList = tl;
		CToken t = getNextToken();
		while (t != null) {
			if (tokenList.hasTokensLeft()) {
				if (t instanceof CIdentifier) {
					if (isKeyword(t)) {
						if (t.getID().equals("if")) {
							
						} else if (t.getID().equals("while")) {

						} else if (t.getID().equals("for")) {

						}
					} else {
						// Parse a variable declaration or an assignment statement
						putTokenBack();
						parseAssignment();
					}
				} else {
					// all statements start with an identifier, how'd we get here
				}
			}
			t = getNextToken();
		}
	}

	public List<Statement> getStatementList() {
		return statementList;
	}

	private void parseAssignment() {
		AssignmentStatement as = new AssignmentStatement();
		CToken t = getNextToken();

		as.setLhs(t);
		t = getNextToken();

		if (t instanceof CSpecial) {
			// assignment statement
			if ((Character) t.getValue() == '=') {
				ArrayList<CToken> rhs = new ArrayList<CToken>();
				t = getNextToken();
				while ((!(t instanceof CWhitespace)) && t != null) {
					rhs.add(t);
					t = getNextToken();
				}
				as.setRhs(rhs);
			}
		} else if (t instanceof CWhitespace) {
			// variable declaration
			as.setRhs(null);
		}
		LOGGER.info("Created assignment statement: " + as.toString());
		addStatement(as);
	}

	private CToken getNextToken() {
		return tokenList.getNextToken();
	}

	private void putTokenBack() {
		tokenList.putTokenBack();
	}

	private void addStatement(Statement s) {
		statementList.add(s);
	}

	private boolean isKeyword(CToken t) {
		return keywords.contains(t);
	}
}

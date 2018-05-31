package parser;

import java.util.ArrayList;
import java.util.logging.Logger;

import statement.Statement;
import statement.AssignmentStatement;
import token.CIdentifier;
import token.CSpecial;
import token.CToken;
import token.CWhitespace;
import tokenizer.Tokenizer;

/**
 * Parser class parses the tokenlist from tokenizer and creates statements out of them
 * 
 * @author Casey
 *
 */
public class Parser {
	private static final Logger LOGGER = Logger.getLogger(Tokenizer.class.getName());
	private final String[] keywords = new String[] { "import", "class", "public", "private", "if", "else", "while",
			"do", "for", "in", "break", "continue", "return", "new", "true", "false", "null" };

	private ArrayList<CToken> tokenList;
	private ArrayList<Statement> statementList;
	private int currentToken;

	public Parser(ArrayList<CToken> t) {
		currentToken = 0;
		tokenList = t;
		statementList = new ArrayList<>();
	}

	public void parse() {
		CToken t;
		t = getNextToken();
		while (t != null) {
			if (currentToken <= tokenList.size()) {
				if (t instanceof CIdentifier) {
					if (isKeyword(t)) {
						if (t.getID().equals("if")) {

						} else if (t.getID().equals("while")) {

						} else if (t.getID().equals("for")) {

						}
					} else {
						// Parse a variable declaration or an assignment
						// statement
						// x = 1
						// x
						putTokenBack();
						parseAssignment();
					}
				} else {
					// all statements start with an identifier, how'd we get
					// here
				}
			}
			t = getNextToken();
		}
	}

	public ArrayList<Statement> getStatementList() {
		return statementList;
	}

	private void parseAssignment() {
		AssignmentStatement as = new AssignmentStatement();
		CToken t;
		t = getNextToken();

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
		CToken t;
		if (currentToken < tokenList.size()) {
			t = tokenList.get(currentToken);
		} else {
			t = null;
		}
		currentToken++;
		return t;
	}

	private void putTokenBack() {
		currentToken--;
	}

	private void addStatement(Statement s) {
		statementList.add(s);
	}

	private boolean isKeyword(CToken t) {
		for (int i = 0; i < keywords.length; i++) {
			if (t.getID().equals(keywords[i])) {
				return true;
			}
		}
		return false;
	}
}

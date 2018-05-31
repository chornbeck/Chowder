package statement;

import java.util.ArrayList;

import token.CToken;

/**
 * Used to represent an assignment statement from the source code. some variable type = some expression
 * 
 * @author Casey
 *
 */
public class AssignmentStatement extends Statement {

	private CToken lhs;
	private ArrayList<CToken> rhs;

	public AssignmentStatement() {
		lhs = null;
		rhs = null;
	}

	public CToken getLhs() {
		return lhs;
	}

	public void setLhs(CToken lhs) {
		this.lhs = lhs;
	}

	public ArrayList<CToken> getRhs() {
		return rhs;
	}

	public void setRhs(ArrayList<CToken> rhs) {
		this.rhs = rhs;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(lhs.getID()).append(" ");
		if (rhs != null) {
			for (CToken token : rhs) {
				builder.append(token.getID()).append(" ");
			}
		}
		return builder.toString();
	}
}

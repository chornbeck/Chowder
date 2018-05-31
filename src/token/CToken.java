package token;

public class CToken {
	// ascii representation of the literal token
	private String id;
	protected Object value;

	public CToken(String s) {
		id = s;
	}

	public String getID() {
		return id;
	}

	public Object getValue() {
		return value;
	}
}

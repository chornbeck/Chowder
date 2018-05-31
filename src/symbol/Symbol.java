package symbol;

public class Symbol {
	// Name of the symbol (user defined or auto generated for a literal)
	private String name;
	// Value of the symbol
	protected Object value;

	public Symbol(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}

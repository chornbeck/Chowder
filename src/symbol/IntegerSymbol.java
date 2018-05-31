package symbol;

public class IntegerSymbol extends Symbol {

	public IntegerSymbol(String n, String v) {
		super(n);
		value = Integer.parseInt(v);
	}

}

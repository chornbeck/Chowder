package symbol;

public class DoubleSymbol extends Symbol {

	public DoubleSymbol(String n, String v) {
		super(n);
		value = Double.parseDouble(v);
	}

}

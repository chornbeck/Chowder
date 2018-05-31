package token;

public class CDouble extends CToken {

	public CDouble(String s) {
		super(s);
		value = Double.parseDouble(s);
	}

}

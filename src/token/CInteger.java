package token;

public class CInteger extends CToken {

	public CInteger(String s) {
		super(s);
		value = Integer.parseInt(s);
	}

}

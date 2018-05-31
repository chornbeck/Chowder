package token;

public class CWhitespace extends CToken {

	public CWhitespace(String s) {
		super(s);
		value = new Character(s.charAt(0));
	}
}

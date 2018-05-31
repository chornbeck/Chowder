package token;

public class CSpecial extends CToken {

	public CSpecial(String s) {
		super(s);
		value = new Character(s.charAt(0));
	}
}

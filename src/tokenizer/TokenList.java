package tokenizer;

import java.util.ArrayList;
import java.util.List;

import token.CToken;

/**
 * Class to handle the manipulation of the token list.
 * This class contains the entire parsed tokens for a program
 * @author Casey
 *
 */
public class TokenList {
	private List<CToken> tokenList;
	private int currentToken;
	
	public TokenList(){
		currentToken = 0;
		tokenList = new ArrayList<>();
	}
	
	public void addToken(CToken t){
		tokenList.add(t);
	}
	
	public CToken getNextToken(){
		CToken t;
		if (currentToken < tokenList.size()) {
			t = tokenList.get(currentToken);
		} else {
			t = null;
		}
		currentToken++;
		return t;
	}
	
	public void putTokenBack(){
		currentToken--;
	}
	
	public boolean hasTokensLeft(){
		if(currentToken <= tokenList.size()){
			return true;
		}
		return false;
	}
}

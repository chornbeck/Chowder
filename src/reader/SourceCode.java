package reader;

public class SourceCode {
	private int currentChar;
	private StringBuilder sourceCode;
	
	public SourceCode(){
		currentChar = 0;
		sourceCode = new StringBuilder();
	}
	
	public void addSource(String s){
		sourceCode.append(s);
	}
	
	public char getNextChar(){
		char c;
		if (currentChar < sourceCode.length()) {
			c = sourceCode.charAt(currentChar);
		} else {
			c = '\0';
		}
		currentChar++;
		return c;
	}
	
	public void putCharBack(){
		 currentChar--;
	}
	
	public boolean hasCharsLeft(){
		if(currentChar <= sourceCode.length()){
			return true;
		}
		return false;
	}

	public boolean currentCharLessThanLength(){
		if(currentChar < sourceCode.length()){
			return true;
		}
		return false;
	}
}

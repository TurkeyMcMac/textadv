package jwmh.dcn;

abstract class PrimitiveCapsule<T> extends Capsule<T> {

	protected PrimitiveCapsule(Character start, Character finish, String selector) {
		super(start, finish, selector);
	}
	
	protected abstract T parseContents(String contents);
	
	@Override
	protected ValueEnd evaluate(String capsule) {
		String contents = "";
	    int terminator = 0;
	    for (int i = 0; i < capsule.length(); i++) {
	    	char currentChar = capsule.charAt(i);
	        if (currentChar == FINISH) {
	        	terminator = i;
	            break;
	        } else {
	            contents += currentChar;
	        }
	 	}
		return new ValueEnd(parseContents(contents), terminator);
	}
	
}

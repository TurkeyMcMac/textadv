package jwmh.dcn;

abstract class EmptyCapsule extends Capsule<Object> {

	public EmptyCapsule(Character start, Character finish, String selector) {
		super(start, finish, selector);
	}

	@Override
	protected ValueEnd evaluate(String capsule) {
		int terminator = 0;
	    for (int i = 0; i < capsule.length(); i++) {
	    	char currentChar = capsule.charAt(i);
	        if (currentChar == FINISH) {
	        	terminator = i;
	            break;
	        }
	 	}
		return new ValueEnd(null, terminator);
	}

}

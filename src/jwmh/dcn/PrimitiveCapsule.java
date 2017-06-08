package jwmh.dcn;

/**
 * This class is the parent
 * of all capsule types which
 * hold primitive, unstructured
 * values.
 * 
 * @author jude
 *
 * @param <T> the type that the capsule holds
 */
abstract class PrimitiveCapsule<T> extends Capsule<T> {

	protected PrimitiveCapsule(Character start, Character finish, String selector) {
		super(start, finish, selector);
	}
	
	/**
	 * Evaluates a string of
	 * contents as an object.
	 * 
	 * @param contents the string to be evaluated
	 * @return the destringified object
	 */
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
	
	@SuppressWarnings("unchecked")
	@Override
	protected String stringify(Object anObject) {
		return START + ((T)anObject).toString() + FINISH;
	}
	
}

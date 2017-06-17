package jwmh.dcn;

import java.io.IOException;
import java.io.Reader;

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

	protected PrimitiveCapsule(Character start, Character finish) {
		super(start, finish);
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
	protected T evaluate(Reader reader)
		throws IOException {
		String contents = "";
		int current;
		while ((current = reader.read()) != -1) {
			char currentChar = (char)current;
			if (currentChar == FINISH) {
				break;
			} else {
				contents += (char)current;
			}
		}
		return parseContents(contents);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected String stringify(Object anObject) {
		return START + ((T)anObject).toString() + FINISH;
	}
	
}

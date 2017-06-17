package jwmh.dcn;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.Reader;

/**
 * This class is the parent
 * of all other data-holding
 * capsules. each instance
 * of any type of capsule is
 * a singleton, and works
 * like a class with static
 * methods.
 * 
 * @author jude
 *
 * @param <T> the type of data that this capsule holds.
 */
abstract class Capsule<T> {

	final char START;
	final char FINISH;
	
	/**
	 * Constructs a capsule that
	 * is recorded by this class.
	 * 
	 * @param start the character marking the start of this capsule
	 * @param finish the character marking the finish of this capsule 
	 * (this value defaults to ';' when left as null)
	 * @param selector the name used to select this capsule type when reading capsules
	 */
	protected Capsule(Character start, Character finish) {
		START = start;
		FINISH = finish;
		capsuleBorders.put(start, finish);
		capsuleStarts.put(start, this);
	}
	/**
	 * A map from the starts of capsules to the ends.
	 */
	protected static Map<Character, Character> capsuleBorders = new HashMap<>();
	/**
	 * A map from the starts of capsules to the capsules themselves.
	 */
	static Map<Character, Capsule<?>> capsuleStarts = new HashMap<>();
	
	/**
	 * Stringifies an object
	 * after figuring out which
	 * capsule it belongs to.
	 * 
	 * @param anObject the object to stringifiy
	 * @return the data capsule notation representation of the object
	 * @throws NoCorrespondingCapsuleException
	 */
	static String doStringify(Object anObject) throws NoCorrespondingCapsuleException {
		for (Capsule<?> c : capsuleStarts.values()) {
			if (c.matches(anObject)) {
				return c.stringify(anObject);
			}
		}
		throw new NoCorrespondingCapsuleException(anObject.toString());
	}
	
	/**
	 * Evaluates a string as a certain type of capsule.
	 * 
	 * @param stringified an object represented by a capsule string
	 * @return the value corresponding to the string along with where in the string
	 * the end of the capsule is
	 */
	protected abstract T evaluate(Reader reader)
		throws IOException;
	
	/**
	 * Stringifies a certain type as a capsule.
	 * 
	 * @param anObject the object to be stringified
	 * @return a string representation of the object
	 */
	protected abstract String stringify(Object anObject);
	
	/**
	 * Checks if an object matches this capsule's contained type.
	 * 
	 * @param anObject the object in question
	 * @return whether or not this object matches this capsule's contained type
	 */
	protected abstract boolean matches(Object anObject);
	
}

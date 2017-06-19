package jwmh.dcn;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the parent
 * of all capsule types which
 * Interpret text.
 * 
 * @author jude
 *
 * @param <T> the type that the capsule holds
 */
abstract class TextCapsule<T> extends Capsule<T> {

	protected TextCapsule(Character start, Character finish) {
		super(start, finish);
	}
	
	/**
	 * Evaluates a piece of text
	 * that has been backslashified.
	 * 
	 * 
	 * @param contents the string to be evaluated
	 * @return the destringified object
	 */
	protected abstract T parseBackslashified(String contents);
	
	@Override
	protected T evaluate(Reader reader)
		throws IOException {
		StringBuffer contents = new StringBuffer();
		int current;
		boolean backslashed = false;
		while ((current = reader.read()) != -1) {
			char currentChar = (char)current;
			if (backslashed) {
				Character escapeCode = escapeCodes.get(currentChar);
				if (escapeCode == null) {
					contents.append(currentChar);
				} else {
					contents.append(escapeCode);
				}
				backslashed = false;
			} else if (currentChar == '\\') {
				backslashed = true;
			} else if (currentChar == FINISH) {
				break;
			} else {
				contents.append(currentChar);
			}
		}
		return parseBackslashified(contents.toString());
	}
	/**
	 * The table of characters which have
	 * a special meaning when following a
	 * backslash.
	 */
	@SuppressWarnings("serial")
	private static Map<Character, Character> escapeCodes = new HashMap<Character, Character>() {{
		put('b', '\b');
		put('f', '\f');
		put('n', '\n');
		put('r', '\r');
		put('t', '\t');
	}};
	
	@SuppressWarnings("unchecked")
	@Override
	protected String stringify(Object anObject) {
		return START + ((T)anObject).toString() + FINISH;
	}
	
}

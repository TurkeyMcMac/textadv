package jwmh.dcn;

import java.io.IOException;
import java.io.Reader;

/**
 * This capsule type always
 * holds the value null.
 * 
 * @author jude
 *
 */
final class NullCapsule extends Capsule<Object> {

	public NullCapsule() {
		super('_', '_');
	}
	
	protected String stringify(Object aNull) {
		return START + "null" + FINISH;
	}

	@Override
	protected boolean matches(Object anObject) {
		return anObject == null;
	}

	@Override
	protected Object evaluate(Reader reader)
			throws IOException {
		int current;
		while ((current = reader.read()) != -1) {
			if ((char)current == FINISH) {
				break;
			}
		}
		return null;
	}
	
}

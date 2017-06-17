package jwmh.dcn;

import java.io.IOException;
import java.io.Reader;

/**
 * This class is the parent
 * of all capsules whose
 * values are always null.
 * 
 * @author jude
 *
 */
abstract class EmptyCapsule extends Capsule<Object> {

	public EmptyCapsule(Character start, Character finish) {
		super(start, finish);
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

package jwmh.dcn;

import java.util.Iterator;
import java.util.List;

/**
 * This class describes the capsule
 * which creates "tapestries" by
 * weaving together strings and
 * characters. This is prettier than
 * alternative methods.
 * 
 * @author jude
 *
 */
class TapestryCapsule extends LongCollectiveCapsule<String> {

	TapestryCapsule() {
		super('&', '~');
	}

	@Override
	protected boolean checkEmpty(Object anObject) {
		return ((Tapestry)anObject).parts.isEmpty();
	}

	@Override
	protected String stringifyItems(Object anObject, StringBuilder tabs) {
		List<Object> valuesToStringify = ((Tapestry)anObject).parts;
		StringBuffer stringified = new StringBuffer();
		for (Object o : valuesToStringify) {
			stringified.append(tabs + Capsule.doStringify(o) + '\n');
		}
		return stringified.toString();
	}

	@Override
	protected String processList(List<Object> valueList) {
		StringBuilder concatted = new StringBuilder();
		for (Iterator<?> i = valueList.iterator(); i.hasNext();) {
			Object o = i.next();
			if (o instanceof String || o instanceof Character) {
				concatted.append(o);
			} else {
				throw new IllegalContentsException("tapestries may only contain strings and characters");
			}
		}
		return concatted.toString();
	}

	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Tapestry;
	}
	
}

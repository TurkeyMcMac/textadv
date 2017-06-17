package jwmh.dcn;

import java.util.List;

/**
 * This capsule type holds
 * lists of values.
 * 
 * @author jude
 *
 */
final class ListCapsule extends LongCollectiveCapsule<List<?>> {

	protected ListCapsule() {
		super('[', ']');
	}
	
	/*
	 * returns the same list that was passed in
	 */
	@Override
	protected List<Object> processList(List<Object> valueList) {
		return valueList;
	}
	
	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof List;
	}

	@Override
	protected boolean checkEmpty(Object anObject) {
		return ((List<?>)anObject).isEmpty();
	}

	@Override
	protected String stringifyItems(Object anObject) {
		StringBuffer stringified = new StringBuffer();
		for (Object o : (List<?>)anObject) {
			stringified.append(LongCollectiveCapsule.tabs + Capsule.doStringify(o) + '\n');
		}
		return stringified.toString();
	}
	
}

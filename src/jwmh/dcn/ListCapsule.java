package jwmh.dcn;

import java.util.List;

/**
 * This capsule type holds
 * lists of values.
 * 
 * @author jude
 *
 */
final class ListCapsule extends WritableCollectiveCapsule<List<?>> {

	protected ListCapsule() {
		super('[', ']', null);
	}
	
	/*
	 * returns the same list that was passed in
	 * */
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
		String stringified = "";
		for (Object o : (List<?>)anObject) {
			stringified += WritableCollectiveCapsule.tabs + Capsule.doStringify(o) + '\n';
		}
		return stringified;
	}
	
}

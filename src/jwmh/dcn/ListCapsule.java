package jwmh.dcn;

import java.util.List;

final class ListCapsule extends WritableCollectiveCapsule<List<?>> {

	protected ListCapsule() {
		super('[', ']', null);
	}
	
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
		String stringified = new String();
		for (Object o : (List<?>)anObject) {
			stringified += WritableCollectiveCapsule.tabs + Capsule.doStringify(o) + '\n';
		}
		return stringified;
	}
	
}

package jwmh.dcn;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * This capsule type holds
 * hash maps of keys and
 * values.
 * 
 * @author jude
 *
 */
final class HashCapsule extends LongCollectiveCapsule<Map<?, ?>> {

	public HashCapsule() {
		super('{', '}');
	}
	
	/*
	 * odd-numbered items are keys,
	 * and even-numbered items are
	 * values.
	 * */
	@Override
	protected Map<Object, Object> processList(List<Object> valueList) {
		int valueNumber = valueList.size();
		if (valueNumber % 2 != 0) {
			throw new IllegalContentsException("key \"" + 
											   valueList.get(valueNumber - 1) + 
											   "\" does not have a corresponding value.");
		}
		Map<Object, Object> hash = new HashMap<>();
		Object key = null;
		for (int i = 0; i < valueNumber; i++) {
			Object currentValue = valueList.get(i);
			if (i % 2 == 0) {
				key = currentValue;
			} else {
				hash.put(key, currentValue);
			}
		}
		return hash;
	}
	
	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Map;
	}

	@Override
	protected boolean checkEmpty(Object anObject) {
		return ((Map<?, ?>)anObject).isEmpty();
	}

	@Override
	protected String stringifyItems(Object anObject, StringBuilder tabs) {
		tabs.append('\t');
		StringBuffer stringified = new StringBuffer();
		for (Object o : ((Map<?, ?>)anObject).keySet()) {
			stringified.append(tabs + Capsule.doStringify(o));
			stringified.append(' ' + Capsule.doStringify(((Map<?, ?>)anObject).get(o)) + '\n');
		}
		tabs.setLength(tabs.length() - 1);
		return stringified.toString();
	}
	
}

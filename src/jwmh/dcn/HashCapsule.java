package jwmh.dcn;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

final class HashCapsule extends CollectiveCapsule<Map<?, ?>> {

	public HashCapsule() {
		super('{', '}', null);
	}
	
	@Override
	protected Map<Object, Object> processList(List<Object> valueList) {
		int valueNumber = valueList.size();
		if (valueNumber % 2 != 0) {
			throw new RuntimeException("key \"" + valueList.get(valueNumber - 1) + "\" does not have a corresponding value.");
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

}

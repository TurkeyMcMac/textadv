package jwmh.dcn;

import java.util.AbstractMap;
import java.util.List;

import jwmh.dcn.exceptions.IllegalContentsException;

final class SetterCapsule extends CollectiveCapsule<AbstractMap.SimpleEntry<?, ?>> implements Ignored {

	public SetterCapsule() {
		super('$', '!', null);
	}

	@Override
	protected AbstractMap.SimpleEntry<Object, Object> processList(List<Object> valueList) {
		if (valueList.size() != 2) {
			throw new IllegalContentsException("setters must contain a name and a value");
		}
		AbstractMap.SimpleEntry<Object, Object> var = new AbstractMap.SimpleEntry<>(valueList.get(0), valueList.get(1));
		return var;
	}

	@Override
	protected String stringify(Object anObject) {
		return null;
	}

	@Override
	protected boolean matches(Object anObject) {
		return false;
	}
	
}

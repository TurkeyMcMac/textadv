package jwmh.dcn;

import java.util.List;

/**
 * This capsule type is
 * evaluated as the value
 * of the variable that it
 * references by name.
 * 
 * @author jude
 *
 */
final class GetterCapsule extends CollectiveCapsule<Object> {

	public GetterCapsule() {
		super('^', '!');
	}
	
	@Override
	protected Object processList(List<Object> valueList) {
		if (valueList.size() != 1) {
			throw new IllegalContentsException("getters must contain only a name");
		}
		Object key = valueList.get(0);
		Object value = VARS.get(key);
		if (VARS.containsKey(key)) //variable exists
			return value;
		else //variable does not exist
			throw new VariableNotFoundException(key.toString());
	}
	
	@Override
	protected String stringify(Object anObject) {
		return START + Capsule.doStringify(((Getter)anObject).variable) + FINISH;
	}

	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Getter;
	}
	
}

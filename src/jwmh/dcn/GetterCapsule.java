package jwmh.dcn;

import java.util.List;

import jwmh.dcn.exceptions.IllegalContentsException;

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
		super('^', '!', null);
	}
	
	@Override
	protected Object processList(List<Object> valueList) {
		if (valueList.size() != 1) {
			throw new IllegalContentsException("getters must contain only a name");
		}
		return valueList.get(0);
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

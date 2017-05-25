package jwmh.dcn;

import java.util.List;

final class ListCapsule extends CollectiveCapsule<List<?>> {

	protected ListCapsule() {
		super('[', ']', null);
	}
	
	@Override
	protected List<Object> processList(List<Object> valueList) {
		return valueList;
	}
	
}

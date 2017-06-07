package jwmh.dcn;

abstract class WritableCollectiveCapsule<T> extends CollectiveCapsule<T> {

	protected WritableCollectiveCapsule(Character start, Character finish, String selector) {
		super(start, finish, selector);
	}
	
	protected static String tabs = new String();
	
	@Override
	protected String stringify(Object anObject) {
		String stringified = Character.toString(START);
		if (!checkEmpty(anObject)) {
			stringified += '\n';
			tabs += '\t';
			stringified += stringifyItems(anObject);
			tabs = tabs.substring(0, tabs.length() - 1);
			stringified += tabs;
		}
		stringified += FINISH;
		return stringified;
	}
	
	protected abstract boolean checkEmpty(Object anObject);
	
	protected abstract String stringifyItems(Object anObject);
	
}

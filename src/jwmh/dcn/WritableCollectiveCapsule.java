package jwmh.dcn;

/**
 * This class is the parent
 * of all collective capsule
 * types which can be written
 * automatically as strings.
 * <p>
 * (Not capsules that deal
 * with variables.)
 * 
 * @author jude
 *
 * @param <T> the type that the capsule contains
 */
abstract class WritableCollectiveCapsule<T> extends CollectiveCapsule<T> {

	protected WritableCollectiveCapsule(Character start, Character finish, String selector) {
		super(start, finish, selector);
	}
	
	/**
	 * The number of tabs
	 * currently active.
	 */
	protected static String tabs = "";
	
	@Override
	protected String stringify(Object anObject) {
		String stringified = Character.toString(START);
		if (!checkEmpty(anObject)) { //if data structure is not empty
			stringified += '\n';
			tabs += '\t'; //add a tab to the counter
			stringified += stringifyItems(anObject); // list items
			tabs = tabs.substring(0, tabs.length() - 1); // remove the tab that was just added
			stringified += tabs; // indent
		}
		stringified += FINISH;
		return stringified;
	}
	
	protected abstract boolean checkEmpty(Object anObject);
	
	protected abstract String stringifyItems(Object anObject);
	
}

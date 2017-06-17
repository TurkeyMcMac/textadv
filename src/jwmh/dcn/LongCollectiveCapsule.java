package jwmh.dcn;

/**
 * This class is the parent
 * of all collective capsule
 * types which span multiple
 * lines.
 * <p>
 * (Not capsules that deal
 * with variables.)
 * 
 * @author jude
 *
 * @param <T> the type that the capsule contains
 */
abstract class LongCollectiveCapsule<T> extends CollectiveCapsule<T> {

	protected LongCollectiveCapsule(Character start, Character finish) {
		super(start, finish);
	}
	
	/**
	 * The number of tabs
	 * currently active.
	 */
	protected static String tabs = "";
	
	@Override
	protected String stringify(Object anObject) {
		StringBuffer stringified = new StringBuffer(Character.toString(START));
		if (!checkEmpty(anObject)) { //if data structure is not empty
			stringified.append('\n');
			tabs += '\t'; //add a tab to the counter
			stringified.append(stringifyItems(anObject)); // list items
			tabs = tabs.substring(0, tabs.length() - 1); // remove the tab that was just added
			stringified.append(tabs); // indent
		}
		stringified.append(FINISH);
		return stringified.toString();
	}
	
	/**
	 * 
	 * @param anObject
	 * @return whether or not the object is empty
	 */
	protected abstract boolean checkEmpty(Object anObject);
	
	/**
	 * 
	 * @param anObject
	 * @return the string representation of the items in an object
	 */
	protected abstract String stringifyItems(Object anObject);
	
}

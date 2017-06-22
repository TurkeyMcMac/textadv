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
	private static StringBuilder tabs = new StringBuilder();
	
	@Override
	protected String stringify(Object anObject) {
		StringBuffer stringified = new StringBuffer(Character.toString(START));
		if (!checkEmpty(anObject)) { //if data structure is not empty
			stringified.append('\n');
			stringified.append(stringifyItems(anObject, tabs));
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
	protected abstract String stringifyItems(Object anObject, StringBuilder tabs);
	
}

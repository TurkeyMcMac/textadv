package jwmh.dcn;

/**
 * This capsule type contains
 * integer values.
 * 
 * @author jude
 *
 */
final class IntegerCapsule extends PrimitiveCapsule<Integer> {

	protected IntegerCapsule() {
		super('#', ';');
	}
	
	@Override
	protected Integer parseContents(String contents) {
		return Integer.parseInt(contents.trim());
	}
	
	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Integer;
	}
	
}

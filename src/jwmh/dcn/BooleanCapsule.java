package jwmh.dcn;

/**
 * This capsule type contains
 * boolean values.
 * 
 * @author jude
 *
 */
final class BooleanCapsule extends PrimitiveCapsule<Boolean> {

	protected BooleanCapsule() {
		super('?', ';');
	}
	
	@Override
	protected Boolean parseContents(String contents) {
		return Boolean.valueOf(contents.trim());
	}
	
	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Boolean;
	}
	
}

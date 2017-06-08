package jwmh.dcn;

/**
 * This capsule type holds
 * comments which are
 * completely ignored.
 * 
 * @author jude
 *
 */
final class CommentCapsule extends EmptyCapsule implements Ignored {

	public CommentCapsule() {
		super('`', '`', null);
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

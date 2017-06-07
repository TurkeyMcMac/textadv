package jwmh.dcn;

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

package jwmh.dcn;

/**
 * This capsule type always
 * holds the value null.
 * 
 * @author jude
 *
 */
final class NullCapsule extends EmptyCapsule {

	public NullCapsule() {
		super('_', '_', null);
		instance = this;
	}
	
	protected static NullCapsule instance;
	
	protected String stringify(Object aNull) {
		return START + "null" + FINISH;
	}

	@Override
	protected boolean matches(Object anObject) {
		return anObject == null;
	}
	
}

package jwmh.dcn;

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

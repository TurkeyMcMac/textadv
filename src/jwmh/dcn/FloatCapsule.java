package jwmh.dcn;

final class FloatCapsule extends PrimitiveCapsule<Float> {

	public FloatCapsule() {
		super('%', null, null);
	}
	
	@Override
	protected Float parseContents(String contents) {
		return Float.parseFloat(contents.trim());
	}
	
	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Float;
	}
	
}

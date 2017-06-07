package jwmh.dcn;

final class IntegerCapsule extends PrimitiveCapsule<Integer> {

	protected IntegerCapsule() {
		super('#', null, null);
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

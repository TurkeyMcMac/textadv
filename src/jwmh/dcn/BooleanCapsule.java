package jwmh.dcn;

final class BooleanCapsule extends PrimitiveCapsule<Boolean> {

	protected BooleanCapsule() {
		super('?', null, null);
	}
	
	@Override
	protected Boolean parseContents(String contents) {
		return Boolean.valueOf(contents.trim());
	}
	
}

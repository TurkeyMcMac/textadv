package jwmh.dcn;

final class StringCapsule extends PrimitiveCapsule<String> {

	public StringCapsule() {
		super('"', '"', null);
	}
	
	@Override
	protected String parseContents(String contents) {
		return contents;
	}

}
package jwmh.dcn;
/**
 * This capsule type holds
 * string values.
 * 
 * @author jude
 *
 */
final class StringCapsule extends PrimitiveCapsule<String> {

	public StringCapsule() {
		super('"', '"');
	}
	/*
	 * returns the same string
	 * value that was passed in.
	 * */
	@Override
	protected String parseContents(String contents) {
		return contents;
	}

	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof String;
	}

}

package jwmh.dcn;
/**
 * This capsule type holds
 * string values.
 * 
 * @author jude
 *
 */
final class StringCapsule extends TextCapsule<String> {

	public StringCapsule() {
		super('"', '"');
	}

	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof String;
	}
	
	/*
	 * returns the same string
	 * value that was passed in.
	 * */
	@Override
	protected String parseBackslashified(String contents) {
		return contents;
	}

}

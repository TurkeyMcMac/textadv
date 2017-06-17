package jwmh.dcn;

/**
 * This capsule type contains
 * character values.
 * 
 * @author jude
 *
 */
final class CharacterCapsule extends PrimitiveCapsule<Character> {

	protected CharacterCapsule() {
		super('\'', '\'');
	}
	
	@Override
	protected Character parseContents(String contents) {
		if (contents.length() != 1) {
			throw new IllegalContentsException("character capsules must contain a single character");
		}
		return contents.charAt(0);
	}
	
	@Override
	protected boolean matches(Object anObject) {
		return anObject instanceof Character;
	}
	
}

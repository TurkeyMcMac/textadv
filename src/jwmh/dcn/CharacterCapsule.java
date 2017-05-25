package jwmh.dcn;

import jwmh.dcn.exceptions.IllegalContentsException;

final class CharacterCapsule extends PrimitiveCapsule<Character> {

	protected CharacterCapsule() {
		super('\'', '\'', null);
	}
	
	@Override
	protected Character parseContents(String contents) {
		if (contents.length() != 1) {
			throw new IllegalContentsException("character capsules must contain a single character");
		}
		return contents.charAt(0);
	}

}

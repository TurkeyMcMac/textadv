package jwmh.dcn.exceptions;

/**
 * This exception is thrown when
 * an object cannot be written
 * because there is no capsule
 * corresponding to its type.
 * 
 * @author jude
 *
 */
public class NoCorrespondingCapsuleException extends RuntimeException {

	private static final long serialVersionUID = -7047221498461127635L;

	public NoCorrespondingCapsuleException(String arg0) {
		super(arg0);
	}

	public NoCorrespondingCapsuleException(Throwable arg0) {
		super(arg0);
	}

	public NoCorrespondingCapsuleException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoCorrespondingCapsuleException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}

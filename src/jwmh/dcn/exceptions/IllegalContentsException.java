package jwmh.dcn.exceptions;

public class IllegalContentsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3333470327076024768L;
	
	public IllegalContentsException() {}

	public IllegalContentsException(String arg0) {
		super(arg0);
	}

	public IllegalContentsException(Throwable arg0) {
		super(arg0);
	}

	public IllegalContentsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
}

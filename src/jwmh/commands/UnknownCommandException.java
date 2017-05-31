package jwmh.commands;

public class UnknownCommandException extends NullPointerException {
	
	private static final long serialVersionUID = 1L;

	public UnknownCommandException() {}

	public UnknownCommandException(String arg0) {
		super("command \"" + arg0 + "\" could not be found.");
	}
	
}

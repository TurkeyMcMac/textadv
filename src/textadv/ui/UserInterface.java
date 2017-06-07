package textadv.ui;

import jwmh.commands.*;

public class UserInterface<T> {
	
	protected CommandSet<T> commands;
	
	public UserInterface(CommandSet<T> commands) {
		this.commands = commands;
	}
	
	public T runArgs(String ... args) {
		return commands.run(args);
	}
	
	public T run(String givenCmd) throws UnknownCommandException {
		return runArgs(givenCmd.trim().split("\\s+"));
	}
	
}

package jwmh.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jwmh.misc.Pair;

public class CommandSet<T> {
	
	protected Map<Pair<String, Integer>, Command<T>> cmdMap = new HashMap<>();
	
	@SafeVarargs
	public CommandSet(Command<T> ... cmds) {
		addCmds(cmds);
	}
	
	public T run(String ... args) throws UnknownCommandException {
		String key = args[0];
		int argNum = args.length - 1;
		Command<T> cmd = cmdMap.get(new Pair<>(key, argNum));
		if (cmd == null) {
			throw new UnknownCommandException(key + " <" + argNum + " arguments>");
		}
		return cmd.run(args);
	}
	
	public T runFrom(String givenCmd) throws UnknownCommandException {
		return run(givenCmd.trim().split("\\s+"));
	}
	
	@SafeVarargs
	public final void addCmds(Command<T> ... cmds) {
		for (Command<T> c : cmds) {
			for (String n : c.names) {
				cmdMap.put(new Pair<>(n, c.argNames.length), c);
			}
		}
	}
	
	public String getString(String cmdName, Integer argNumber) {
		return cmdMap.get(new Pair<>(cmdName, argNumber)).toString();
	}
	
	@Override
	public String toString() {
		Set<String> displaySet = new TreeSet<>();
		for (Command<T> c : cmdMap.values()) {
			displaySet.add(c.toString());
		}
		String stringified = new String();
		for (String s : displaySet) {
			stringified += '\n' + s + '\n';
		}
		return stringified;
	}
	
}

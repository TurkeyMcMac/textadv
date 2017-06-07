package jwmh.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jwmh.misc.Pair;

public class CommandSet<T> {
	
	Map<Pair<String, Integer>, Command<T>> cmdMap = new HashMap<>();
	
	@SafeVarargs
	public CommandSet(Command<T> ... cmds) {
		for (Command<T> c : cmds) {
			for (String n : c.names) {
				cmdMap.put(new Pair<>(n, c.argNames.length), c);
			}
		}
	}
	
	public T run(String ... args) throws UnknownCommandException {
		try {
			return cmdMap.get(new Pair<>(args[0], args.length - 1)).run(args);
		} catch (NullPointerException e) {
			throw new UnknownCommandException();
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

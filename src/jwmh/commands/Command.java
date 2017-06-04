package jwmh.commands;

import java.util.function.Function;

public class Command<U> {
	
	String name;
	String[] argNames = new String[0];
	String info = "...";
	protected Function<String[], U> effect = (args) -> { return null; };

	public Command(String name) {
		this.name = name;
	}
	
	public Command<U> setArgNames(String ... argNames) {
		this.argNames = argNames;
		return this;
	}
	
	public Command<U> setInfo(String info) {
		this.info = info;
		return this;
	}
	
	public Command<U> setEffect(Function<String[], U> effect) {
		this.effect = effect;
		return this;
	}
	
	public U run(String ... args) {
		return effect.apply(args);
	}
	
	@Override
	public String toString() {
		String stringified = name + ' ';
		for (String s : argNames) {
			stringified += '<' + s + "> ";
		}
		stringified += info;
		return stringified;
	}
	
}

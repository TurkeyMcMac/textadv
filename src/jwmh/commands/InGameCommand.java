package jwmh.commands;

import textadv.base.player.PlayerController;

public class InGameCommand<U> extends Command<U> {
	
	private PlayerController controller;
	
	public InGameCommand(String name) {
		super(name);
	}
	
	public InGameCommand<U> setController(PlayerController controller) {
		this.controller = controller;
		return this;
	}
	
	@Override
	public U run(String ... args) {
		if (controller.isSuppressed()) {
			return null;
		} else {
			return effect.apply(args);
		}
	}
	
}
